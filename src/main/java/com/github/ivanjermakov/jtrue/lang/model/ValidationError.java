package com.github.ivanjermakov.jtrue.lang.model;

import java.util.Collections;
import java.util.List;

public class ValidationError {

	public final String message;
	public final List<ValidationError> causes;

	public ValidationError() {
		this("");
	}

	public ValidationError(String message) {
		this(message, Collections.emptyList());
	}

	public ValidationError(List<ValidationError> causes) {
		this("", causes);
	}

	public ValidationError(String message, List<ValidationError> causes) {
		this.message = message;
		this.causes = causes;
	}

	public boolean hasError() {
		return !(message.isEmpty() && causes.isEmpty());
	}

}
