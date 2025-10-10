package com.java.quiz.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.java.quiz.entity.DifficultyLevel;
import com.java.quiz.entity.Question;
import com.java.quiz.payload.ApiResponse;

public interface QuizService {

	ResponseEntity<ApiResponse<List<Question>>> createQuiz(String topic, DifficultyLevel difficultyLevel,
			Long noOfQuestions);

}
