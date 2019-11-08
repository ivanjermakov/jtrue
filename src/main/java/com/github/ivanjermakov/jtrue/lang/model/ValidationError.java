package com.github.ivanjermakov.jtrue.lang.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ValidationError {

	public final String message;
	public final List<ValidationError> causes;
	public final boolean isError;

	public ValidationError(boolean isError, String message) {
		this(isError, message, Collections.emptyList());
	}

	public ValidationError(boolean isError, List<ValidationError> causes) {
		this(isError, "", causes);
	}

	public ValidationError(boolean isError) {
		this(isError, "", Collections.emptyList());
	}

	public ValidationError(boolean isError, String message, List<ValidationError> causes) {
		this.isError = isError;
		this.message = message;
		this.causes = causes;
	}

	public List<ValidationError> flat() {
		return flat(this);
	}

	private List<ValidationError> flat(ValidationError error) {
		if (error.causes.isEmpty()) return Collections.singletonList(error);

		return error.causes
				.stream()
				.flatMap(c -> flat(c).stream())
				.collect(Collectors.toList());
	}

	@Override
	public String toString() {
		if (!isError) return "No validation error occurred";

		String result = "Validation error";

		if (!message.isEmpty()) {
			result = result.concat(": " + message);
		}

		result = result.concat(System.lineSeparator());

		if (!causes.isEmpty()) {
			result = result
					.concat("caused by:\n")
					.concat(causes
							.stream()
							.map(ValidationError::toString)
							.map("    "::concat)
							.collect(Collectors.joining())
					);
		}

		return result;
	}

}
