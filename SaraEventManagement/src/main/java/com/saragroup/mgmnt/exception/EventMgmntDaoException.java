package com.saragroup.mgmnt.exception;

public class EventMgmntDaoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EventMgmntDaoException() {
	}

	public EventMgmntDaoException(String message) {
		super(message);
	}

	public EventMgmntDaoException(Throwable cause) {
		super(cause);
	}

	public EventMgmntDaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public EventMgmntDaoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
