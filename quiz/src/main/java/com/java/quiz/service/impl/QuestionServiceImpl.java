package com.java.quiz.service.impl;

import com.java.quiz.entity.Question;
import com.java.quiz.exception.QuestionNotFoundException;
import com.java.quiz.payload.QuestionDTO;
import com.java.quiz.repository.QuestionRepository;
import com.java.quiz.service.QuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Question createQuestion(QuestionDTO dto) {
		Question question = modelMapper.map(dto, Question.class);
		try {
			return questionRepository.save(question);
		} catch (DataIntegrityViolationException ex) {
			throw new DataIntegrityViolationException("Question number must be unique");
		}
	}

	@Override
	public List<Question> getAllQuestions() {
		return questionRepository.findAll();
	}

	@Override
	public Question getQuestionById(Long id) {
		return questionRepository.findById(id)
				.orElseThrow(() -> new QuestionNotFoundException("Question not found with id: " + id));
	}

	@Override
	public void deleteQuestion(Long id) {
		try {
			Question question = questionRepository.findById(id)
					.orElseThrow(() -> new QuestionNotFoundException("Question not found with id: " + id));
			questionRepository.delete(question);
		} catch (DataIntegrityViolationException ex) {
			throw new DataIntegrityViolationException("Cannot delete question: It may be referenced elsewhere");
		} catch (Exception ex) {
			throw new RuntimeException("Failed to delete question: " + ex.getMessage());
		}
	}

	@Override
	public Question getQuestionByQuestionNumber(Long questionNumber) {
		return questionRepository.findByQuestionNumber(questionNumber).orElseThrow(
				() -> new QuestionNotFoundException("Question not found with question number: " + questionNumber));
	}

	@Override
	public Question updateQuestionByQuestionNumber(Long questionNumber, QuestionDTO dto) {
		Question existing = questionRepository.findByQuestionNumber(questionNumber).orElseThrow(
				() -> new QuestionNotFoundException("Question not found with question number: " + questionNumber));

		modelMapper.map(dto, existing);
		existing.setQuestionNumber(questionNumber); // Preserve uniqueness

		try {
			return questionRepository.save(existing);
		} catch (DataIntegrityViolationException ex) {
			throw new DataIntegrityViolationException("Failed to update: Question number must remain unique");
		}
	}
}
