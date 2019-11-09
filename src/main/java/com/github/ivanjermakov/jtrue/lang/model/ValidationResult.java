package com.github.ivanjermakov.jtrue.lang.model;

public class ValidationResult {

	public final boolean isValid;
	public final ValidationTree validationTree;

	public ValidationResult(boolean isValid, ValidationTree validationTree) {
		this.isValid = isValid;
		this.validationTree = validationTree;
	}

}
