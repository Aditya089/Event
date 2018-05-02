package com.saragroup.mgmnt.exception;

public class EventServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorCode="SERVER_ERROR";

	public EventServiceException(String errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public EventServiceException(String errorCode) {
		this.errorCode = errorCode;
	}

	public EventServiceException(Throwable cause) {
		super(cause);
	}

	public String getErrorCode() {
		return errorCode;
	}

}
