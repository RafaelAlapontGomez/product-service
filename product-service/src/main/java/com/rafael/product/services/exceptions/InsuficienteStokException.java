package com.rafael.product.services.exceptions;

@SuppressWarnings("serial")
public class InsuficienteStokException extends Exception {
    public InsuficienteStokException(String errorMessage) {
        super(errorMessage);
    }
}
