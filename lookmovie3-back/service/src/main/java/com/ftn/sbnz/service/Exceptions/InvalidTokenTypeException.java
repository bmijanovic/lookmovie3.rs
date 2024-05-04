package com.ftn.sbnz.service.Exceptions;

public class InvalidTokenTypeException extends RuntimeException {
	public InvalidTokenTypeException(String message) {
		super(message);
	}
}
