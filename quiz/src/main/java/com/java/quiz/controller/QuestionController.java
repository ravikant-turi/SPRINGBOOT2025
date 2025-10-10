package com.java.quiz.controller;

import com.java.quiz.entity.Question;
import com.java.quiz.payload.ApiResponse;
import com.java.quiz.payload.QuestionDTO;
import com.java.quiz.service.QuestionService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ModelMapper modelMapper;

    // Create a new question
    @PostMapping
    public ResponseEntity<ApiResponse<Question>> createQuestion(@Valid @RequestBody QuestionDTO dto) {
        Question question = questionService.createQuestion(dto);
        ApiResponse<Question> response = new ApiResponse<>("SUCCESS", "Question created successfully", question);
        return ResponseEntity.ok(response);
    }

    // Get all questions
    @GetMapping
    public ResponseEntity<ApiResponse<List<QuestionDTO>>> getAllQuestions() {
        List<Question> questions = questionService.getAllQuestions();
        List<QuestionDTO> dtos = questions.stream()
                .map(q -> modelMapper.map(q, QuestionDTO.class))
                .collect(Collectors.toList());
        ApiResponse<List<QuestionDTO>> response = new ApiResponse<>("SUCCESS", "Questions fetched successfully", dtos);
        return ResponseEntity.ok(response);
    }

    // Get question by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<QuestionDTO>> getQuestionById(@PathVariable Long id) {
        Question question = questionService.getQuestionById(id);
        QuestionDTO dto = modelMapper.map(question, QuestionDTO.class);
        ApiResponse<QuestionDTO> response = new ApiResponse<>("SUCCESS", "Question fetched successfully", dto);
        return ResponseEntity.ok(response);
    }

    // Get question by questionNumber
    @GetMapping("/number/{questionNumber}")
    public ResponseEntity<ApiResponse<QuestionDTO>> getByQuestionNumber(@PathVariable Long questionNumber) {
        Question question = questionService.getQuestionByQuestionNumber(questionNumber);
        QuestionDTO dto = modelMapper.map(question, QuestionDTO.class);
        ApiResponse<QuestionDTO> response = new ApiResponse<>("SUCCESS", "Question fetched successfully", dto);
        return ResponseEntity.ok(response);
    }

    // Update question by questionNumber
    @PutMapping("/number/{questionNumber}")
    public ResponseEntity<ApiResponse<QuestionDTO>> updateByQuestionNumber(
            @PathVariable Long questionNumber,
            @Valid @RequestBody QuestionDTO dto) {
        Question updated = questionService.updateQuestionByQuestionNumber(questionNumber, dto);
        QuestionDTO updatedDto = modelMapper.map(updated, QuestionDTO.class);
        ApiResponse<QuestionDTO> response = new ApiResponse<>("SUCCESS", "Question updated successfully", updatedDto);
        return ResponseEntity.ok(response);
    }

    // Delete question by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        ApiResponse<String> response = new ApiResponse<>("SUCCESS", "Question deleted successfully", null);
        return ResponseEntity.ok(response);
    }
}
