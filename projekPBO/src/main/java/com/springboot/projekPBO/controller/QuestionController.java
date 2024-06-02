package com.springboot.projekPBO.controller;

import com.springboot.projekPBO.model.Question;
import com.springboot.projekPBO.service.IQuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED; // Mengimpor status CREATED dari kelas HttpStatus untuk memberi respons status 201 saat menciptakan sumber daya

@CrossOrigin("http://localhost:5173") // Mengizinkan permintaan lintas asal dari URL yang ditentukan
@RestController // Menandai kelas ini sebagai pengendali RESTful yang akan menangani permintaan web
@RequestMapping("/api/quizzes") // Menetapkan jalur dasar untuk semua permintaan yang ditangani oleh kelas ini
@RequiredArgsConstructor // Membuat konstruktor dengan parameter yang diperlukan menggunakan Lombok
public class QuestionController {
	private final IQuestionService questionService;

    @PostMapping("/create-new-question")
    public ResponseEntity<Question> createQuestion(@Valid @RequestBody Question question){ // Mendefinisikan metode untuk membuat pertanyaan baru dengan validasi data
        Question createdQuestion = questionService.createQuestion(question); // Membuat pertanyaan baru menggunakan questionService
        return ResponseEntity.status(CREATED).body(createdQuestion); // Memberi respons dengan status CREATED dan mengembalikan pertanyaan yang dibuat
    }

    @GetMapping("/all-questions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        List<Question> questions = questionService.getAllQuestions(); // Mendapatkan semua pertanyaan menggunakan questionService
        return ResponseEntity.ok(questions);// Memberi respons dengan status OK dan mengembalikan daftar pertanyaan
    }

    @GetMapping("/question/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException { // Mendefinisikan metode untuk mendapatkan pertanyaan berdasarkan ID
        Optional<Question> theQuestion = questionService.getQuestionById(id);
        if (theQuestion.isPresent()){ // Memeriksa apakah pertanyaan ditemukan
            return ResponseEntity.ok(theQuestion.get());
        }else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @PutMapping("/question/{id}/update") // Menentukan bahwa metode ini akan menangani permintaan PUT ke /api/quizzes/question/{id}/update
    public ResponseEntity<Question> updateQuestion( // Mendefinisikan ID pertanyaan yang akan diperbarui dan objek pertanyaan yang diperbarui
            @PathVariable Long id, @RequestBody Question question) throws ChangeSetPersister.NotFoundException {
        Question updatedQuestion = questionService.updateQuestion(id, question); // Memberi respons dengan status OK dan mengembalikan pertanyaan yang diperbarui
        return ResponseEntity.ok(updatedQuestion);
    }

    @DeleteMapping("/question/{id}/delete")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id){
        questionService.deleteQuestion(id); // Menghapus pertanyaan menggunakan questionService
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/subjects")
    public ResponseEntity<List<String>> getAllSubjects(){
        List<String> subjects = questionService.getAllSubjects();
        return ResponseEntity.ok(subjects);
    }

    @GetMapping("/quiz/fetch-questions-for-user")
    public ResponseEntity<List<Question>> getQuestionsForUser(
            @RequestParam Integer numOfQuestions, @RequestParam String subject){ // Mendefinisikan jumlah pertanyaan dan subjek yang diminta
        List<Question> allQuestions = questionService.getQuestionsForUser(numOfQuestions, subject);

        List<Question> mutableQuestions = new ArrayList<>(allQuestions);
        Collections.shuffle(mutableQuestions);

        int availableQuestions = Math.min(numOfQuestions, mutableQuestions.size());
        List<Question> randomQuestions = mutableQuestions.subList(0, availableQuestions);
        return ResponseEntity.ok(randomQuestions);
    }
}
