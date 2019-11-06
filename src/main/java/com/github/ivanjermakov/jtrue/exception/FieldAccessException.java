package com.github.ivanjermakov.jtrue.exception;

public class FieldAccessException extends LangException {

	public FieldAccessException() {
	}

	public FieldAccessException(String message) {
		super(message);
	}

	public FieldAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	public FieldAccessException(Throwable cause) {
		super(cause);
	}

	public FieldAccessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
