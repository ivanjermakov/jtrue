package com.github.ivanjermakov.jtrue.exception;

public class SyntaxException extends LangException {

	public SyntaxException() {
	}

	public SyntaxException(String message) {
		super(message);
	}

	public SyntaxException(String message, Throwable cause) {
		super(message, cause);
	}

	public SyntaxException(Throwable cause) {
		super(cause);
	}

}
