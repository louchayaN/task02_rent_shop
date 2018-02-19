package com.corporation.task02.dao.exception;

public class ResourceUpdateDaoException extends Exception {

	private static final long serialVersionUID = 1L;

	public ResourceUpdateDaoException() {
		super();
	}

	public ResourceUpdateDaoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ResourceUpdateDaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResourceUpdateDaoException(String message) {
		super(message);
	}

	public ResourceUpdateDaoException(Throwable cause) {
		super(cause);
	}

	
}
