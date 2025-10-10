package com.java.quiz.payload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizResultDTO {
    private int totalQuestions;
    private int correctAnswers;
    private int score;
    private List<Long> incorrectQuestionIds;
}
