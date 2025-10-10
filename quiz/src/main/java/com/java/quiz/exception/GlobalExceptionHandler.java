//package com.java.quiz.exception;
//
//import com.java.quiz.payload.ApiResponse;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.MissingServletRequestParameterException;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//	// 1. Handle custom not found exception
//	@ExceptionHandler(QuestionNotFoundException.class)
//	public ResponseEntity<ApiResponse<String>> handleQuestionNotFound(QuestionNotFoundException ex) {
//		ApiResponse<String> response = new ApiResponse<>("ERROR", ex.getMessage(), null);
//		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//	}
//
//	// 2. Handle validation errors from DTOs
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
//		Map<String, String> errors = new HashMap<>();
//		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
//			errors.put(error.getField(), error.getDefaultMessage());
//		}
//		ApiResponse<Map<String, String>> response = new ApiResponse<>("ERROR", "Validation failed", errors);
//		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//	}
//
//	// 3. Handle missing request parameters
//	@ExceptionHandler(MissingServletRequestParameterException.class)
//	public ResponseEntity<ApiResponse<String>> handleMissingParams(MissingServletRequestParameterException ex) {
//		String message = "Missing required parameter: " + ex.getParameterName();
//		ApiResponse<String> response = new ApiResponse<>("ERROR", message, null);
//		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//	}
//
//	// 4. Handle type mismatch (e.g., passing string instead of number)
//	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
//	public ResponseEntity<ApiResponse<String>> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
//		String message = "Invalid value for parameter '" + ex.getName() + "'. Expected type: "
//				+ ex.getRequiredType().getSimpleName();
//		ApiResponse<String> response = new ApiResponse<>("ERROR", message, null);
//		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//	}
//
////     5. Handle malformed JSON or unreadable request body
////    @ExceptionHandler(HttpMessageNotReadableException.class)
////    public ResponseEntity<ApiResponse<String>> handleUnreadableMessage(HttpMessageNotReadableException ex) {
////        ApiResponse<String> response = new ApiResponse<>("ERROR", "Malformed JSON request", null);
////        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
////    }
//
//	// 6. Handle database constraint violations (e.g., unique constraint)
//	@ExceptionHandler(DataIntegrityViolationException.class)
//	public ResponseEntity<ApiResponse<String>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
//		ApiResponse<String> response = new ApiResponse<>("ERROR",
//				"Database constraint violation: " + ex.getRootCause().getMessage(), null);
//		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
//	}
//
//	// 7. Catch-all for unhandled exceptions
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<ApiResponse<String>> handleGeneralException(Exception ex) {
//		ApiResponse<String> response = new ApiResponse<>("ERROR", "Internal Server Error", null);
//		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//
//	@ExceptionHandler(QuizNotFoundException.class)
//	public ResponseEntity<String> handleQuizNotFound(QuizNotFoundException ex) {
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
//	}
//}
