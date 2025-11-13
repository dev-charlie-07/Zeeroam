package com.zeeroam.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = { CustomException.class })
	public ResponseEntity<CustomErrorResponse> unsupporterMediaType(CustomException ex, HttpServletRequest request) {
		return handleException(ex.getMessage(), request, ex.getHttpStatus());
	}
	
	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<CustomErrorResponse> handleAllExceptions(Exception ex, HttpServletRequest request) {
		ex.printStackTrace();
		return handleException(ex.getMessage(), request, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	private ResponseEntity<CustomErrorResponse> handleException(String message, HttpServletRequest request, HttpStatus status) {
		CustomErrorResponse errors = new CustomErrorResponse();
		errors.setTimestamp(LocalDateTime.now());
		errors.setResponseMessage(message);
		errors.setResponseCode(String.valueOf(status.value()));
		log.error("final response : {}", errors);
		return new ResponseEntity<>(errors, status);
	}
	
}
