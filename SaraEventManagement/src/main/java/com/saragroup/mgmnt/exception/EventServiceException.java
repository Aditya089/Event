package com.saragroup.mgmnt.exception;

public class EventServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String errorCode="EVENT_SERVER_EXCEPTION";
	
	public EventServiceException(String errorCode) {
		this.errorCode = errorCode;
	}

	public EventServiceException(Throwable cause) {
		super(cause);
	}
	
	public EventServiceException(String errorCode, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
	}
	
	public EventServiceException(String errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}
}
