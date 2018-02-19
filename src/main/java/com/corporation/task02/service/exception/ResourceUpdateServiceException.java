package com.corporation.task02.service.exception;

public class ResourceUpdateServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	public ResourceUpdateServiceException() {
		super();
	}

	public ResourceUpdateServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ResourceUpdateServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResourceUpdateServiceException(String message) {
		super(message);
	}

	public ResourceUpdateServiceException(Throwable cause) {
		super(cause);
	}

}
