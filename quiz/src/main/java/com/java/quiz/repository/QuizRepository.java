package com.java.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.quiz.entity.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
