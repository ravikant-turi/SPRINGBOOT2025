package com.java.quiz.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Question content must not be blank")
	@Size(max = 500, message = "Question content must not exceed 500 characters")
	private String content;

	@NotBlank(message = "Option A must not be blank")
	private String optionA;

	@NotBlank(message = "Option B must not be blank")
	private String optionB;

	@NotBlank(message = "Option C must not be blank")
	private String optionC;

	@NotBlank(message = "Option D must not be blank")
	private String optionD;

	@NotBlank(message = "Correct answer must not be blank")
	private String correctAnswer;

	@NotBlank(message = "Category must not be blank")
	private String category;

	@NotNull(message = "Question number must not be null")
	@Column(unique = true)
	private Long questionNumber;
}
