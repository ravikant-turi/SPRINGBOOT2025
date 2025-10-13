package com.java.quiz.service.impl;

import java.util.List;
import static com.java.quiz.constants.MessageConstants.*;
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

@Service
public class QuestionServiceImpl implements QuestionService {
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ApiResponse<Question> createQuestion(QuestionDTO dto) {
		Question question = modelMapper.map(dto, Question.class);
		Question savedQuestion = questionRepository.save(question);
		return new ApiResponse<>(SUCCESS, QUESTION_CREATED, savedQuestion);
	}

	@Override
	public ApiResponse<List<Question>> getAllQuestions() {
		List<Question> questions = questionRepository.findAll();
		return new ApiResponse<>(SUCCESS, DATA_FOUND, questions);
	}

	@Override
	public ApiResponse<Question> getQuestionById(Long id) {
		Question questionFound = questionRepository.findById(id)
				.orElseThrow(() -> new QuestionNotFoundException(QUESTION_NOT_FOUND_BY_ID + id));
		return new ApiResponse<>(SUCCESS, DATA_FOUND, questionFound);
	}

	@Override
	public ApiResponse<String> deleteQuestion(Long id) {
		Question question = questionRepository.findById(id)
				.orElseThrow(() -> new QuestionNotFoundException(QUESTION_NOT_FOUND_BY_ID + id));
		questionRepository.delete(question);
		return new ApiResponse<>(SUCCESS, DATA_DELETED, QUESTION_ID_PREFIX + id);
	}

	@Override
	public ApiResponse<Question> getQuestionByQuestionNumber(Long questionNumber) {
		Question questionFound = questionRepository.findByQuestionNumber(questionNumber)
				.orElseThrow(() -> new QuestionNotFoundException(QUESTION_NOT_FOUND_BY_NUMBER + questionNumber));
		return new ApiResponse<>(SUCCESS, DATA_FOUND, questionFound);
	}

	@Override
	public ApiResponse<Question> updateQuestionByQuestionNumber(Long questionNumber, QuestionDTO dto) {
		Question questionFound = questionRepository.findByQuestionNumber(questionNumber)
				.orElseThrow(() -> new QuestionNotFoundException(QUESTION_NOT_FOUND_BY_NUMBER + questionNumber));

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
		return new ApiResponse<>(SUCCESS, QUESTION_UPDATED, updatedQuestion);
	}

	@Override
	public ApiResponse<List<Question>> findByCategory(String category) {
		List<Question> questions = questionRepository.findByCategory(category);
		return new ApiResponse<>(SUCCESS, DATA_FOUND, questions);
	}

	@Override
	public ApiResponse<List<Question>> findByDeficultyLevel(DifficultyLevel deficultyLevel) {
		List<Question> questions = questionRepository.findByDeficultyLevel(deficultyLevel);
		return new ApiResponse<>(SUCCESS, DATA_FOUND, questions);
	}

}
