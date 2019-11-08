package com.github.ivanjermakov.jtrue.exception;

public class LangException extends RuntimeException {

	public LangException() {
	}

	public LangException(String message) {
		super(message);
	}

	public LangException(String message, Throwable cause) {
		super(message, cause);
	}

	public LangException(Throwable cause) {
		super(cause);
	}

}
