package com.java.quiz.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.java.quiz.entity.DifficultyLevel;
import com.java.quiz.entity.Question;
import com.java.quiz.payload.ApiResponse;
import com.java.quiz.repository.QuestionRepository;
import com.java.quiz.service.QuizService;

public class QuizServiceImpl implements QuizService {

	@Autowired
	private QuizService quizService;

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ResponseEntity<ApiResponse<List<Question>>> createQuiz(String topic, DifficultyLevel difficultyLevel,
			Long noOfQuestions) {
    
	
		return null;
	}

}
