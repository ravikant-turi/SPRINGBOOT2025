package com.java.quiz.service.impl;

import static com.java.quiz.constants.MessageConstants.DATA_DELETED;
import static com.java.quiz.constants.MessageConstants.DATA_FOUND;
import static com.java.quiz.constants.MessageConstants.QUESTION_CREATED;
import static com.java.quiz.constants.MessageConstants.QUESTION_ID_PREFIX;
import static com.java.quiz.constants.MessageConstants.QUESTION_NOT_FOUND_BY_ID;
import static com.java.quiz.constants.MessageConstants.QUESTION_NOT_FOUND_BY_NUMBER;
import static com.java.quiz.constants.MessageConstants.QUESTION_UPDATED;
import static com.java.quiz.constants.MessageConstants.SUCCESS;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.quiz.entity.DifficultyLevel;
import com.java.quiz.entity.Question;
import com.java.quiz.exception.QuestionNotFoundException;
import com.java.quiz.payload.ApiResponse;
import com.java.quiz.payload.QuestionDTO;
import com.java.quiz.repository.QuestionRepository;
import com.java.quiz.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class QuestionServiceImpl implements QuestionService {
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private ModelMapper modelMapper;
	private static final Logger logger = LoggerFactory.getLogger(QuestionServiceImpl.class);

	@Override
	public ApiResponse<Question> createQuestion(QuestionDTO dto) {
		logger.info("Creating new question with content: {}", dto.getContent());
		Question question = modelMapper.map(dto, Question.class);
		Question savedQuestion = questionRepository.save(question);
		logger.debug("Question saved with ID: {}", savedQuestion.getId());
		return new ApiResponse<>(SUCCESS, QUESTION_CREATED, savedQuestion);
	}

	@Override
	public ApiResponse<List<Question>> getAllQuestions() {
		logger.info("Fetching all questions from repository");
		List<Question> questions = questionRepository.findAll();
		logger.debug("Total questions retrieved: {}", questions.size());
		return new ApiResponse<>(SUCCESS, DATA_FOUND, questions);
	}

	@Override
	public ApiResponse<Question> getQuestionById(Long id) {
		logger.info("Fetching question by ID: {}", id);
		Question questionFound = questionRepository.findById(id).orElseThrow(() -> {
			logger.warn("Question not found with ID: {}", id);
			return new QuestionNotFoundException(QUESTION_NOT_FOUND_BY_ID + id);
		});
		logger.debug("Question found: {}", questionFound.getContent());
		return new ApiResponse<>(SUCCESS, DATA_FOUND, questionFound);
	}

	@Override
	public ApiResponse<String> deleteQuestion(Long id) {
		logger.info("Deleting question with ID: {}", id);
		Question question = questionRepository.findById(id).orElseThrow(() -> {
			logger.warn("Question not found for deletion with ID: {}", id);
			return new QuestionNotFoundException(QUESTION_NOT_FOUND_BY_ID + id);
		});
		questionRepository.delete(question);
		logger.debug("Question deleted successfully with ID: {}", id);
		return new ApiResponse<>(SUCCESS, DATA_DELETED, QUESTION_ID_PREFIX + id);
	}

	@Override
	public ApiResponse<Question> getQuestionByQuestionNumber(Long questionNumber) {
		logger.info("Fetching question by question number: {}", questionNumber);
		Question questionFound = questionRepository.findByQuestionNumber(questionNumber).orElseThrow(() -> {
			logger.warn("Question not found with question number: {}", questionNumber);
			return new QuestionNotFoundException(QUESTION_NOT_FOUND_BY_NUMBER + questionNumber);
		});
		logger.debug("Question retrieved: {}", questionFound.getContent());
		return new ApiResponse<>(SUCCESS, DATA_FOUND, questionFound);
	}

	@Override
	public ApiResponse<Question> updateQuestionByQuestionNumber(Long questionNumber, QuestionDTO dto) {
		logger.info("Updating question with question number: {}", questionNumber);
		Question questionFound = questionRepository.findByQuestionNumber(questionNumber).orElseThrow(() -> {
			logger.warn("Question not found for update with question number: {}", questionNumber);
			return new QuestionNotFoundException(QUESTION_NOT_FOUND_BY_NUMBER + questionNumber);
		});

		questionFound.setContent(dto.getContent());
		questionFound.setOptionA(dto.getOptionA());
		questionFound.setOptionB(dto.getOptionB());
		questionFound.setOptionC(dto.getOptionC());
		questionFound.setOptionD(dto.getOptionD());
		questionFound.setCorrectAnswer(dto.getCorrectAnswer());
		questionFound.setCategory(dto.getCategory());
		questionFound.setDeficultyLevel(dto.getDeficultyLevel());
		questionFound.setQuestionNumber(questionNumber);

		Question updatedQuestion = questionRepository.save(questionFound);
		logger.debug("Question updated successfully with ID: {}", updatedQuestion.getId());
		return new ApiResponse<>(SUCCESS, QUESTION_UPDATED, updatedQuestion);
	}

	@Override
	public ApiResponse<List<Question>> findByCategory(String category) {
		logger.info("Finding questions by category: {}", category);
		List<Question> questions = questionRepository.findByCategory(category);
		logger.debug("Questions found in category '{}': {}", category, questions.size());
		return new ApiResponse<>(SUCCESS, DATA_FOUND, questions);
	}

	@Override
	public ApiResponse<List<Question>> findByDeficultyLevel(DifficultyLevel deficultyLevel) {
		logger.info("Finding questions by difficulty level: {}", deficultyLevel);
		List<Question> questions = questionRepository.findByDeficultyLevel(deficultyLevel);
		logger.debug("Questions found with difficulty level '{}': {}", deficultyLevel, questions.size());
		return new ApiResponse<>(SUCCESS, DATA_FOUND, questions);
	}
}
