package com.java.quiz.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.quiz.entity.DifficultyLevel;
import com.java.quiz.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

	Optional<Question> findByQuestionNumber(Long questionNumber);

	List<Question> findByCategory(String category);

	List<Question> findByDeficultyLevel(DifficultyLevel level);

	// ✅ Combined filter with both fields
	List<Question> findByCategoryAndDeficultyLevel(String category, DifficultyLevel deficultyLevel);

}