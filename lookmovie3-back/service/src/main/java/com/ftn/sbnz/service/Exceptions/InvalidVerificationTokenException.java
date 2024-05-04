package com.ftn.sbnz.service.Exceptions;

public class InvalidVerificationTokenException extends RuntimeException {
	public InvalidVerificationTokenException(String message) {
		super(message);
	}
}

