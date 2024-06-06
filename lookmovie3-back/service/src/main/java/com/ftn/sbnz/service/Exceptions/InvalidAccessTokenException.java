package com.ftn.sbnz.service.Exceptions;

public class InvalidAccessTokenException extends RuntimeException {
	public InvalidAccessTokenException(String message) {
		super(message);
	}
}