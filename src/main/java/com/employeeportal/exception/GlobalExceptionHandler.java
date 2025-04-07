package com.employeeportal.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.employeeportal.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	

	 @ExceptionHandler(ResourceNotFoundException.class)
	    public ResponseEntity<Object> handleResourceNotFoundException(
	            ResourceNotFoundException ex, WebRequest request) {
	        String error = "Resource not found";
	        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), error, ex.getMessage());
	        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	    }

	    @ExceptionHandler(BadRequestException.class)
	    public ResponseEntity<Object> handleBadRequestException(
	            BadRequestException ex, WebRequest request) {
	        String error = "Bad request";
	        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), error, ex.getMessage());
	        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	    }
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<String> handleNotFoundException(NotFoundException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}


}


	

	


