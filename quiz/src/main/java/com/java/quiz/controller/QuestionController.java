package com.java.quiz.controller;

import com.java.quiz.entity.DifficultyLevel;
import com.java.quiz.entity.Question;
import com.java.quiz.payload.ApiResponse;
import com.java.quiz.payload.QuestionDTO;
import com.java.quiz.service.QuestionService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

import static com.java.quiz.constants.MessageConstants.*;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private ModelMapper modelMapper;

	private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);

	@PostMapping
	public ResponseEntity<ApiResponse<Question>> createQuestion(@Valid @RequestBody QuestionDTO dto) {
		logger.info("Creating question: {}", dto.getContent());
		Question question = questionService.createQuestion(dto).getData();
		return ResponseEntity.ok(new ApiResponse<>(SUCCESS, QUESTION_CREATED, question));
	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<QuestionDTO>>> getAllQuestions() {
		logger.info("Fetching all questions");
		List<Question> questions = questionService.getAllQuestions().getData();
		List<QuestionDTO> dtos = questions.stream().map(q -> modelMapper.map(q, QuestionDTO.class))
				.collect(Collectors.toList());
		return ResponseEntity.ok(new ApiResponse<>(SUCCESS, DATA_FOUND, dtos));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<QuestionDTO>> getQuestionById(@PathVariable Long id) {
		logger.info("Fetching question by ID: {}", id);
		Question question = questionService.getQuestionById(id).getData();
		QuestionDTO dto = modelMapper.map(question, QuestionDTO.class);
		return ResponseEntity.ok(new ApiResponse<>(SUCCESS, DATA_FOUND, dto));
	}

	@GetMapping("/number/{questionNumber}")
	public ResponseEntity<ApiResponse<QuestionDTO>> getByQuestionNumber(@PathVariable Long questionNumber) {
		logger.info("Fetching question by number: {}", questionNumber);
		Question question = questionService.getQuestionByQuestionNumber(questionNumber).getData();
		QuestionDTO dto = modelMapper.map(question, QuestionDTO.class);
		return ResponseEntity.ok(new ApiResponse<>(SUCCESS, DATA_FOUND, dto));
	}

	@PutMapping("/number/{questionNumber}")
	public ResponseEntity<ApiResponse<QuestionDTO>> updateByQuestionNumber(@PathVariable Long questionNumber,
			@Valid @RequestBody QuestionDTO dto) {
		logger.info("Updating question number: {}", questionNumber);
		Question updated = questionService.updateQuestionByQuestionNumber(questionNumber, dto).getData();
		QuestionDTO updatedDto = modelMapper.map(updated, QuestionDTO.class);
		return ResponseEntity.ok(new ApiResponse<>(SUCCESS, QUESTION_UPDATED, updatedDto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<String>> deleteQuestion(@PathVariable Long id) {
		logger.info("Deleting question ID: {}", id);
		ApiResponse<String> response = questionService.deleteQuestion(id);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/category/{category}")
	public ResponseEntity<ApiResponse<List<Question>>> getByCategory(@PathVariable String category) {
		logger.info("Fetching questions by category: {}", category);
		ApiResponse<List<Question>> response = questionService.findByCategory(category);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/difficulty/{level}")
	public ResponseEntity<ApiResponse<List<Question>>> getByDifficulty(@PathVariable DifficultyLevel level) {
		logger.info("Fetching questions by difficulty level: {}", level);
		ApiResponse<List<Question>> response = questionService.findByDeficultyLevel(level);
		return ResponseEntity.ok(response);
	}
}
