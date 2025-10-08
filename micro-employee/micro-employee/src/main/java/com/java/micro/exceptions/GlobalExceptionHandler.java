//package com.java.micro.exceptions;
//
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//import com.java.micro.payload.ApiResponse;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<ApiResponse<Map<Object, Object>>> handleGenericException(Exception exception) {
//		ApiResponse<Map<Object, Object>> apiResponse = new ApiResponse<>("ERROR", "SOMETHING_WENT_WRONG",
//				Collections.emptyMap());
//		return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationException(
//			MethodArgumentNotValidException ex) {
//		Map<String, String> errors = new HashMap<>();
//		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
//		for (FieldError fr : fieldErrors) {
//			errors.put(fr.getField(), fr.getDefaultMessage());
//		}
//		ApiResponse<Map<String, String>> response = new ApiResponse<>("ERROR", "VALIDATION_FAILED", errors);
//		return ResponseEntity.badRequest().body(response);
//	}
//
//	@ExceptionHandler(ResourceNotFoundException.class)
//	public ResponseEntity<ApiResponse<Map<String, String>>> handleResourceNotFoundException(
//			ResourceNotFoundException ex) {
//		ApiResponse<Map<String, String>> response = new ApiResponse<>("ERROR", ex.getMessage(), Collections.emptyMap());
//		return ResponseEntity.badRequest().body(response);
//	}
//
//}
