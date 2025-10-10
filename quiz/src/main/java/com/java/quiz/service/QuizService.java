package com.java.quiz.service;

import java.util.List;

import com.java.quiz.entity.DifficultyLevel;
import com.java.quiz.entity.Question;
import com.java.quiz.payload.ApiResponse;
import com.java.quiz.payload.QuizResultDTO;
import com.java.quiz.payload.QuizSubmissionDTO;

public interface QuizService {

	ApiResponse<List<Question>> createQuiz(String topic, DifficultyLevel difficultyLevel, Long noOfQuestions);

	QuizResultDTO evaluateQuiz(QuizSubmissionDTO submission);

	ApiResponse<List<Question>> findQuizById(Long id);

}
