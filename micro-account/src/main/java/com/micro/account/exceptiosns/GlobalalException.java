package com.micro.account.exceptiosns;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.micro.account.payload.ApiResponse;

@ControllerAdvice
public class GlobalalException {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<Map<String, String>>> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException ex) {

		Map<String, String> map = new HashMap<String, String>();

		List<FieldError> errors = ex.getBindingResult().getFieldErrors();

		for (FieldError fieldError : errors) {
			map.put(fieldError.getField(), fieldError.getDefaultMessage());
		}

		ApiResponse<Map<String, String>> errorResponse = new ApiResponse<Map<String, String>>("ERROR",
				"VALIDATION FAILED", map);

		return ResponseEntity.badRequest().body(errorResponse);

	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse<Map<String, String>>> handleResourceNotFoundException(
			ResourceNotFoundException ex) {

		ApiResponse<Map<String, String>> errroResponse = new ApiResponse<Map<String, String>>("ERROR", ex.getMessage(),
				Collections.emptyMap());

		return ResponseEntity.badRequest().body(errroResponse);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Map<Object, Object>>> handleException(Exception es) {

		ApiResponse<Map<Object, Object>> apiResponse = new ApiResponse<>("ERROR", "SOMETHING_WENT_WRONG",
				Collections.emptyMap());
		return ResponseEntity.internalServerError().body(apiResponse);
	}

	@ExceptionHandler(DuplicateResourceException.class)
	public ResponseEntity<ApiResponse<Map<Object, Object>>> handleException(DuplicateResourceException ex) {

		ApiResponse<Map<Object, Object>> apiResponse = new ApiResponse<>("ERROR", ex.getMessage(),
				Collections.emptyMap());
		return ResponseEntity.badRequest().body(apiResponse);
	}

}
