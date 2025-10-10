package com.java.quiz.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.quiz.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

	Optional<Question> findByQuestionNumber(Long questionNumber);

}