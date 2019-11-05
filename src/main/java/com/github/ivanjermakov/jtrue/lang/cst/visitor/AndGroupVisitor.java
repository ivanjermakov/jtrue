package com.github.ivanjermakov.jtrue.lang.cst.visitor;

import com.github.ivanjermakov.antlr.JtrueParser;
import com.github.ivanjermakov.jtrue.exception.SyntaxException;
import com.github.ivanjermakov.jtrue.lang.cst.VisitorConfiguration;
import com.github.ivanjermakov.jtrue.lang.model.ValidationError;
import com.github.ivanjermakov.jtrue.lang.model.ValidationResult;

import java.util.List;
import java.util.stream.Collectors;

public class AndGroupVisitor<T> implements LangVisitor<ValidationResult> {

	private final JtrueParser.AndGroupContext andGroup;
	private final VisitorConfiguration<T> config;

	public AndGroupVisitor(JtrueParser.AndGroupContext andGroup, VisitorConfiguration<T> config) {
		this.andGroup = andGroup;
		this.config = config;
	}

	@Override
	public ValidationResult visit() {
		if (andGroup.validationRules() != null) {
			List<ValidationResult> results = new ValidationRulesVisitor<>(andGroup.validationRules(), config).visit();

			boolean isValid = results
					.stream()
					.allMatch(r -> r.isValid);

			ValidationError error = new ValidationError(
					results
							.stream()
							.filter(r -> !r.isValid)
							.map(r -> r.validationError)
							.collect(Collectors.toList())
			);

			return new ValidationResult(
					isValid,
					error
			);
		}

		throw new SyntaxException("'andGroup' node must contain 'validationRules' node");
	}

}
