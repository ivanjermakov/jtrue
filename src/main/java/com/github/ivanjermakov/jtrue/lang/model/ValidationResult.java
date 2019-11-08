package com.github.ivanjermakov.jtrue.lang.model;

public class ValidationResult {

	public final boolean isValid;
	public final ValidationError validationError;

	public ValidationResult(boolean isValid, ValidationError validationError) {
		this.isValid = isValid;
		this.validationError = validationError;
	}

}
