package com.github.ivanjermakov.jtrue.lang.cst.visitor;

import com.github.ivanjermakov.antlr.JtrueParser;
import com.github.ivanjermakov.jtrue.exception.SyntaxException;
import com.github.ivanjermakov.jtrue.lang.cst.VisitorConfiguration;
import com.github.ivanjermakov.jtrue.lang.model.ValidationResult;
import com.github.ivanjermakov.jtrue.lang.model.ValidationTree;

import java.util.List;
import java.util.stream.Collectors;

public class OrGroupVisitor<T> implements LangVisitor<ValidationResult> {

	private final JtrueParser.OrGroupContext orGroup;
	private final VisitorConfiguration<T> config;

	public OrGroupVisitor(JtrueParser.OrGroupContext orGroup, VisitorConfiguration<T> config) {
		this.orGroup = orGroup;
		this.config = config;
	}

	@Override
	public ValidationResult visit() {
		if (orGroup.validationRules() != null) {
			List<ValidationResult> results = new ValidationRulesVisitor<>(orGroup.validationRules(), config).visit();

			boolean isValid = results
					.stream()
					.anyMatch(r -> r.isValid);

			ValidationTree error = new ValidationTree(
					isValid,
					results
							.stream()
							.map(r -> r.validationTree)
							.collect(Collectors.toList())
			);

			return new ValidationResult(isValid, error);
		}

		throw new SyntaxException("'orGroup' node must contain 'validationRules' node");
	}

}
