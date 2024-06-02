package com.springboot.projekPBO.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Question {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String question;
    @NotBlank
    private String subject;
    @NotBlank
    private String questionType;


    @ElementCollection
    private List<String> choices;


    @ElementCollection
    private List<String> correctAnswers;


	public String getQuestion() {
		return question;
	}


	public void setQuestion(String question) {
		this.question = question;
	}


	public List<String> getChoices() {
		return choices;
	}


	public void setChoices(List<String> choices) {
		this.choices = choices;
	}


	public List<String> getCorrectAnswers() {
		return correctAnswers;
	}


	public void setCorrectAnswers(List<String> correctAnswers) {
		this.correctAnswers = correctAnswers;
	}
    
    
}
