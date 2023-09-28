package com.exptracker.exception;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.exptracker.entity.ErrorDetail;

@ControllerAdvice
public class GlobalException {

	@ExceptionHandler(CustomerException.class)
	// @ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorDetail> handleException(MethodArgumentNotValidException ex, WebRequest request) {

		ErrorDetail errorDetail = new ErrorDetail(new Date(), ex.getBindingResult().getFieldError().getDefaultMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(errorDetail, HttpStatus.NOT_ACCEPTABLE);

	}

}
