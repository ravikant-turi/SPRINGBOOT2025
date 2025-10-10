package com.java.quiz.service;

import com.java.quiz.payload.QuestionDTO;
import com.java.quiz.entity.Question;

import java.util.List;

public interface QuestionService {

	Question createQuestion(QuestionDTO questionDTO);

	List<Question> getAllQuestions();

	Question getQuestionById(Long id);

	Question getQuestionByQuestionNumber(Long quetionNumber);

	Question updateQuestionByQuestionNumber(Long quetionNumber, QuestionDTO questionDTO);

	void deleteQuestion(Long id);
}