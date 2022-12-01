package com.lcwd.store.excetions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.lcwd.store.dtos.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	// method for handling resource not found exception exception
	@ExceptionHandler(ResourceNotFountException.class)
	public ResponseEntity<ApiResponse> handleRunTimeException(ResourceNotFountException e) {

		logger.info("Resource Not Found Exception Generated : {} ", e.getMessage());
		return new ResponseEntity<ApiResponse>(ApiResponse.builder().message(e.getMessage()).success(false).build(),
				HttpStatus.NOT_FOUND);
	}

	// method for handling Invalid age exception
	@ExceptionHandler(InvalidAgeException.class)
	public ResponseEntity<ApiResponse> handleInvalidAge(InvalidAgeException e) {
		logger.info("Invalid Age Generated : {} ", e.getMessage());
		return new ResponseEntity<ApiResponse>(ApiResponse.builder().message(e.getMessage()).success(false).build(),
				HttpStatus.BAD_REQUEST);
	}

	// method for handling validation exception

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException ex) {

		Map<String, String> responseMap = new HashMap<>();
		List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
		allErrors.forEach(error -> {
			String messageString = error.getDefaultMessage();
			String fieldName = ((FieldError) error).getField();
			responseMap.put(fieldName, messageString);
		});

		return new ResponseEntity<Map<String, String>>(responseMap, HttpStatus.BAD_REQUEST);

	}

	// method for handling Invalid age exception
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<ApiResponse> handlerEmptyResultFromDatabase(EmptyResultDataAccessException e) {
		logger.info("Result is Empty : {} ", e.getMessage());
		return new ResponseEntity<ApiResponse>(
				ApiResponse.builder().message("Result is Empty: Data does not exists").success(false).build(),
				HttpStatus.BAD_REQUEST);
	}

	// method for handling Invalid age exception
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ApiResponse> handleBadRequest(BadRequestException e) {
		logger.info("Result is Empty : {} ", e.getMessage());
		return new ResponseEntity<ApiResponse>(
				ApiResponse.builder().message(e.getMessage()).success(false).build(),
				HttpStatus.BAD_REQUEST);
	}

}
