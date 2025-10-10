package com.java.quiz.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.java.quiz.entity.DifficultyLevel;
import com.java.quiz.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

	Optional<Question> findByQuestionNumber(Long questionNumber);

	List<Question> findByCategory(String category);

	List<Question> findByDeficultyLevel(DifficultyLevel level);

	// ✅ Combined filter with both fields
	List<Question> findByCategoryAndDeficultyLevel(String category, DifficultyLevel deficultyLevel);

	List<Question> findByCategoryAndDeficultyLevel(String category, DifficultyLevel deficultyLevel, Pageable pageable);

	@Query(value = "SELECT * FROM question WHERE category = :category AND deficulty_level = :level ORDER BY RAND() LIMIT :limit", nativeQuery = true)
	List<Question> findRandomQuestions(@Param("category") String category, @Param("level") String level,
			@Param("limit") int limit);

}