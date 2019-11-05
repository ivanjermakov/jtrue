package com.github.ivanjermakov.jtrue.lang.cst.visitor;

import com.github.ivanjermakov.antlr.JtrueParser;
import com.github.ivanjermakov.jtrue.exception.SyntaxException;
import com.github.ivanjermakov.jtrue.lang.cst.VisitorConfiguration;
import com.github.ivanjermakov.jtrue.lang.model.ValidationResult;

public class NegateVisitor<T> implements LangVisitor<ValidationResult> {

	private final JtrueParser.NegateContext negate;
	private final VisitorConfiguration<T> config;

	public NegateVisitor(JtrueParser.NegateContext negate, VisitorConfiguration<T> config) {
		this.negate = negate;
		this.config = config;
	}

	@Override
	public ValidationResult visit() {
		if (negate.group() != null) {
			ValidationResult result = new GroupVisitor<>(negate.group(), config).visit();
			return new ValidationResult(!result.isValid, result.validationError);
		}

		throw new SyntaxException("'negate' node must contain 'group' node");
	}
}
