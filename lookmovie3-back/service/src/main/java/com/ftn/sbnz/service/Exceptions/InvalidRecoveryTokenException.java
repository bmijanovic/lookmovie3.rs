package com.ftn.sbnz.service.Exceptions;

public class InvalidRecoveryTokenException extends RuntimeException {
	public InvalidRecoveryTokenException(String message) {
		super(message);
	}
}

