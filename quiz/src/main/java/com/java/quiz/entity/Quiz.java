package com.java.quiz.entity;

import java.util.List;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Quiz {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Quiz title is required")
	@Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
	private String title;

	@NotBlank(message = "Category is required")
	@Size(min = 2, max = 50, message = "Category must be between 2 and 50 characters")
	private String category;

	@NotNull(message = "Difficulty level must be selected")
	@Enumerated(EnumType.STRING)
	private DifficultyLevel difficultyLevel;

	@Min(value = 1, message = "Quiz must contain at least 1 question")
	@Max(value = 50, message = "Quiz cannot contain more than 50 questions")
	private int numberOfQuestions;

	@NotEmpty(message = "Quiz must include questions")
	@ManyToMany
	private List<Question> questions;
}
