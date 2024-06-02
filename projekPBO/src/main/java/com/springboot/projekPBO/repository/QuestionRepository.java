package com.springboot.projekPBO.repository;

import com.springboot.projekPBO.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.net.ContentHandler;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> { // Mendeklarasikan antarmuka QuestionRepository yang mewarisi JpaRepository untuk Question dengan tipe ID Long


    @Query("SELECT DISTINCT q.subject FROM Question q")// Menentukan kueri JPA khusus untuk menemukan subjek yang berbeda
    List<String> findDistinctSubject();
    Page<Question> findBySubject(String subject, Pageable pageable); // Mendeklarasikan metode untuk menemukan pertanyaan berdasarkan subjek
    
}
