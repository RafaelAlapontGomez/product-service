package com.rafael.product.services.exceptions;

@SuppressWarnings("serial")
public class NoDataFoundException extends Exception {
    public NoDataFoundException(String errorMessage) {
        super(errorMessage);
    }
}
