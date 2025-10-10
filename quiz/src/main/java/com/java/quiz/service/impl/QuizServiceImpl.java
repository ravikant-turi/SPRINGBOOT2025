package com.java.quiz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.java.quiz.entity.DifficultyLevel;
import com.java.quiz.entity.Question;
import com.java.quiz.entity.Quiz;
import com.java.quiz.exception.QuizNotFoundException;
import com.java.quiz.payload.ApiResponse;
import com.java.quiz.payload.QuizResultDTO;
import com.java.quiz.payload.QuizSubmissionDTO;
import com.java.quiz.repository.QuestionRepository;
import com.java.quiz.repository.QuizRepository;
import com.java.quiz.service.QuizService;

@Service
public class QuizServiceImpl implements QuizService {

	@Autowired
	private QuizRepository quizRepository;

	@Autowired
	private QuestionRepository questionRepository;

//	@Autowired
//	private ModelMapper modelMapper;

	@Override
	public ApiResponse<List<Question>> createQuiz(String topic, DifficultyLevel difficultyLevel, Long noOfQuestions) {

		// Convert Long to int for Pageable
		int limit = noOfQuestions.intValue();

		// Create a Pageable object to limit results
		Pageable pageable = PageRequest.of(0, limit);

		// Fetch filtered and limited questions from the repository
		List<Question> questions = questionRepository.findByCategoryAndDeficultyLevel(topic, difficultyLevel, pageable);

		// Handle case when no questions are found
		if (questions.isEmpty()) {
			return new ApiResponse<List<Question>>("SUCCESS",
					"No questions found for the given topic and difficulty level", null);
		}
		Quiz quiz = new Quiz();
		quiz.setTitle(topic + " Quiz");
		quiz.setCategory(topic);
		quiz.setDifficultyLevel(difficultyLevel);
		quiz.setNumberOfQuestions(questions.size());
		quiz.setQuestions(questions);

		quizRepository.save(quiz);

		// Create a success response
		return new ApiResponse<List<Question>>("SUCCESS", "Quiz created successfully", questions);

	}

	@Override
	public QuizResultDTO evaluateQuiz(QuizSubmissionDTO submission) {
		if (submission == null || submission.getQuizId() == null || submission.getAnswers() == null) {
			throw new IllegalArgumentException("Invalid submission: quizId and answers are required");
		}

		Quiz quiz = quizRepository.findById(submission.getQuizId())
				.orElseThrow(() -> new QuizNotFoundException("Quiz with ID " + submission.getQuizId() + " not found"));

		int correct = 0;
		List<Long> incorrect = new ArrayList<>();

		for (Question question : quiz.getQuestions()) {
			String submittedAnswer = submission.getAnswers().get(question.getId());

			if (submittedAnswer == null) {
				incorrect.add(question.getId()); // unanswered question
				continue;
			}

			if (submittedAnswer.equalsIgnoreCase(question.getCorrectAnswer())) {
				correct++;
			} else {
				incorrect.add(question.getId());
			}
		}

		QuizResultDTO result = new QuizResultDTO();
		result.setTotalQuestions(quiz.getQuestions().size());
		result.setCorrectAnswers(correct);
		result.setScore(correct); // 1 point per correct answer
		result.setIncorrectQuestionIds(incorrect);

		return result;
	}

	@Override
	public ApiResponse<List<Question>> findQuizById(Long id) {
	    Quiz quiz = quizRepository.findById(id)
	            .orElseThrow(() -> new QuizNotFoundException("Quiz with ID " + id + " not found"));

	    List<Question> questions = quiz.getQuestions();

	    return new ApiResponse<>("SUCCESS", "Quiz retrieved successfully", questions);
	}

}
