package com.java.quiz.service;

import java.util.List;

import com.java.quiz.entity.DifficultyLevel;
import com.java.quiz.entity.Question;
import com.java.quiz.payload.QuestionDTO;

public interface QuestionService {

	Question createQuestion(QuestionDTO questionDTO);

	List<Question> getAllQuestions();

	Question getQuestionById(Long id);

	Question getQuestionByQuestionNumber(Long quetionNumber);

	Question updateQuestionByQuestionNumber(Long quetionNumber, QuestionDTO questionDTO);

	void deleteQuestion(Long id);

	List<Question> findByCategory(String category);

//	    List<Question> findByDeficultyLevel(Long deficultyLevel); // if using numeric

	// OR if using enum:
	List<Question> findByDeficultyLevel(DifficultyLevel deficultyLevel);
}