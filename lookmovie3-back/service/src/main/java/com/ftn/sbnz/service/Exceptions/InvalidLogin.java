package com.ftn.sbnz.service.Exceptions;

import javax.naming.AuthenticationException;

public class InvalidLogin extends AuthenticationException {
	public InvalidLogin(String message) {
		super(message);
	}
}

