package com.saragroup.mgmnt.exception;

public class AuthenticationException extends EventServiceException{

	private static final long serialVersionUID = -2701739765164897574L;

	public AuthenticationException(String errorCode, String message, Throwable cause) {
		super(errorCode, message, cause);
	}

	public AuthenticationException(String errorCode) {
		super(errorCode);
	}

	public AuthenticationException(Throwable cause) {
		super(cause);
	}

	
	
}
