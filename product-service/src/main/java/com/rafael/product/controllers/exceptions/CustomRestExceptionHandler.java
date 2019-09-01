package com.rafael.product.controllers.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.rafael.product.services.exceptions.NoDataFoundException;
import com.rafael.product.services.exceptions.ProductExistException;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ NoDataFoundException.class })
	public ResponseEntity<Object> handleConstraintViolation_NoDataFoundException(
	  NoDataFoundException ex, WebRequest request) {
	    List<String> errors = new ArrayList<String>();
	 
	    ApiError apiError = 
	      new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), errors);
	    return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler({ ProductExistException.class })
	public ResponseEntity<Object> handleConstraintViolation_ProductExistException(
			ProductExistException ex, WebRequest request) {
	    List<String> errors = new ArrayList<String>();
	 
	    ApiError apiError = 
	      new ApiError(HttpStatus.ALREADY_REPORTED, ex.getMessage(), errors);
	    return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

}
