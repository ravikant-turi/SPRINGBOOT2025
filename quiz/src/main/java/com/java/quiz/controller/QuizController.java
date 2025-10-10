package com.java.quiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.quiz.entity.DifficultyLevel;
import com.java.quiz.entity.Question;
import com.java.quiz.payload.ApiResponse;
import com.java.quiz.payload.QuizResultDTO;
import com.java.quiz.payload.QuizSubmissionDTO;
import com.java.quiz.service.QuizService;

@RestController
@RequestMapping("api/quiz/")
public class QuizController {

	@Autowired
	private QuizService quizService;

	@GetMapping("topic/{topic}/level/{level}/total/{total}")
	public ResponseEntity<ApiResponse<List<Question>>> createQuiz(@PathVariable String topic,
			@PathVariable DifficultyLevel level, @PathVariable Long total) {

		ApiResponse<List<Question>> apiResponse = this.quizService.createQuiz(topic, level, total);

		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

	}

	@PostMapping("/submit")
	public ResponseEntity<QuizResultDTO> submitQuiz(@RequestBody QuizSubmissionDTO submission) {
		QuizResultDTO result = quizService.evaluateQuiz(submission);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<List<Question>>> getQuizById(@PathVariable Long id) {
		ApiResponse<List<Question>> response = quizService.findQuizById(id);
		return ResponseEntity.ok(response);
	}

}
