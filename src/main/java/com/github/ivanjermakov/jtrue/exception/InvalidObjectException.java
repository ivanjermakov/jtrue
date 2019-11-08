package com.github.ivanjermakov.jtrue.exception;

public class InvalidObjectException extends RuntimeException {

	public InvalidObjectException() {
	}

	public InvalidObjectException(String message) {
		super(message);
	}

	public InvalidObjectException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidObjectException(Throwable cause) {
		super(cause);
	}

}
