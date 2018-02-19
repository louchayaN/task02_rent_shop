package com.corporation.task02.service.exception;

public class ShopInitializeServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	public ShopInitializeServiceException() {
		super();
	}

	public ShopInitializeServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ShopInitializeServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ShopInitializeServiceException(String message) {
		super(message);
	}

	public ShopInitializeServiceException(Throwable cause) {
		super(cause);
	}

	
}
