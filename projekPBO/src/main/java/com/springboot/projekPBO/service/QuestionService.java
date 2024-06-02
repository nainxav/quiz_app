package com.springboot.projekPBO.service;

import com.springboot.projekPBO.model.Question;
import com.springboot.projekPBO.repository.QuestionRepository;
import com.springboot.projekPBO.service.IQuestionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service // Menandai kelas ini sebagai komponen layanan dalam konteks aplikasi Spring
@RequiredArgsConstructor // Membuat konstruktor dengan parameter yang diperlukan menggunakan Lombok
public class QuestionService implements IQuestionService {
	private final QuestionRepository questionRepository;

    @Override
    public Question createQuestion(Question question) {
        return questionRepository.save(question); // Menyimpan pertanyaan baru menggunakan questionRepository dan mengembalikan hasilnya
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll(); // Mengambil semua pertanyaan dari repositori menggunakan questionRepository
    }

    @Override
    public Optional<Question> getQuestionById(Long id) {
        return questionRepository.findById(id); // Mengambil pertanyaan berdasarkan ID dari repositori menggunakan questionRepository
    }

    @Override
    public List<String> getAllSubjects() {
        return questionRepository.findDistinctSubject();
    }

    @Override
    public Question updateQuestion(Long id, Question question) throws ChangeSetPersister.NotFoundException {
        Optional<Question> theQuestion = this.getQuestionById(id);
        if (theQuestion.isPresent()){
            Question updatedQuestion = theQuestion.get();
            updatedQuestion.setQuestion(question.getQuestion());
            updatedQuestion.setChoices(question.getChoices());
            updatedQuestion.setCorrectAnswers(question.getCorrectAnswers());
            return questionRepository.save(updatedQuestion);
        }else {
            throw new ChangeSetPersister.NotFoundException();
        }

    }
    @Override
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }
    @Override
    public List<Question> getQuestionsForUser(Integer numOfQuestions, String subject) {
        Pageable pageable = PageRequest.of(0, numOfQuestions);
        return questionRepository.findBySubject(subject, pageable).getContent();
    }
}
