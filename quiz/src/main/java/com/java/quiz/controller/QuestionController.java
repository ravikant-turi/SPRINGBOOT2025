package com.java.quiz.controller;

import com.java.quiz.entity.Question;
import com.java.quiz.payload.ApiResponse;
import com.java.quiz.payload.QuestionDTO;
import com.java.quiz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping
    public ResponseEntity<ApiResponse<Question>> createQuestion(@RequestBody QuestionDTO dto) {
        Question question = questionService.createQuestion(dto);
        ApiResponse<Question> response = new ApiResponse<>("SUCCESS", "Question created successfully", question);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Question>>> getAllQuestions() {
        List<Question> questions = questionService.getAllQuestions();
        ApiResponse<List<Question>> response = new ApiResponse<>("SUCCESS", "Questions fetched successfully", questions);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Question>> getQuestionById(@PathVariable Long id) {
        Question question = questionService.getQuestionById(id);
        ApiResponse<Question> response = new ApiResponse<>("SUCCESS", "Question fetched successfully", question);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        ApiResponse<String> response = new ApiResponse<>("SUCCESS","Question deleted successfully", null);
        return ResponseEntity.ok(response);
    }
}