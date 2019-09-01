package com.rafael.product.services.exceptions;

@SuppressWarnings("serial")
public class ProductExistException extends Exception {

	public ProductExistException(String errorMessage) {
		super(errorMessage);
	}
}
