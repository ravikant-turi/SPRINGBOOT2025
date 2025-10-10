package com.java.quiz.payload;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizSubmissionDTO {
	private Long quizId;
	private Map<Long, String> answers; // questionId → selectedOption
}
