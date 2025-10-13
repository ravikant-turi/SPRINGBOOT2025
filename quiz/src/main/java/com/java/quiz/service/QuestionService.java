package com.java.quiz.service;

import java.util.List;

import com.java.quiz.entity.DifficultyLevel;
import com.java.quiz.entity.Question;
import com.java.quiz.payload.ApiResponse;
import com.java.quiz.payload.QuestionDTO;

public interface QuestionService {

	ApiResponse<Question> createQuestion(QuestionDTO questionDTO);

	ApiResponse<List<Question>> getAllQuestions();

	ApiResponse<Question> getQuestionById(Long id);

	ApiResponse<Question> getQuestionByQuestionNumber(Long quetionNumber);

	ApiResponse<Question> updateQuestionByQuestionNumber(Long quetionNumber, QuestionDTO questionDTO);

	ApiResponse<String> deleteQuestion(Long id);

	ApiResponse<List<Question>> findByCategory(String category);

//	    List<Question> findByDeficultyLevel(Long deficultyLevel); // if using numeric

	// OR if using enum:
	ApiResponse<List<Question>> findByDeficultyLevel(DifficultyLevel deficultyLevel);

}