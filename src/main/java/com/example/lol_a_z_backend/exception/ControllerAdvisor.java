package com.example.lol_a_z_backend.exception;

import com.example.lol_a_z_backend.security.exception.UserExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j @ControllerAdvice public class ControllerAdvisor extends ResponseEntityExceptionHandler {
	@ExceptionHandler(UserExistsException.class) public ResponseEntity<ApiError> handleNoSuchElementException(UserExistsException ex) {
		log.error("User already exists!", ex);
		ApiError apiError = new ApiError("User already exists!", ex.getMessage());
		return new ResponseEntity<>(apiError, HttpStatus.ALREADY_REPORTED);
	}

}
