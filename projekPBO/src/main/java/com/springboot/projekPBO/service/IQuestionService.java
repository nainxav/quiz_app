package com.springboot.projekPBO.service;

import com.springboot.projekPBO.model.Question;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.util.Optional;

public interface IQuestionService {
    Question createQuestion(Question question); // Mendeklarasikan metode untuk membuat pertanyaan baru

    List<Question> getAllQuestions(); // Mendeklarasikan metode untuk mendapatkan semua pertanyaan

    Optional<Question> getQuestionById(Long id); // Mendeklarasikan metode untuk mendapatkan pertanyaan berdasarkan ID

    List<String> getAllSubjects(); // Mendeklarasikan metode untuk mendapatkan semua subjek pertanyaan

    Question updateQuestion(Long id, Question question) throws ChangeSetPersister.NotFoundException; // Mendeklarasikan metode untuk memperbarui pertanyaan berdasarkan ID

    void  deleteQuestion(Long id); // Mendeklarasikan metode untuk menghapus pertanyaan berdasarkan ID

    List<Question> getQuestionsForUser(Integer numOfQuestions, String subject); // Mendeklarasikan metode untuk mendapatkan pertanyaan untuk pengguna
}
