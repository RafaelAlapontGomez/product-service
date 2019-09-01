package com.rafael.product.services.exceptions;

@SuppressWarnings("serial")
public class ProductNotExistException extends Exception {

	public ProductNotExistException(String errorMessage) {
		super(errorMessage);
	}
}
