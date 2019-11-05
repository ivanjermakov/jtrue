package com.github.ivanjermakov.jtrue.lang.cst.visitor;

import com.github.ivanjermakov.antlr.JtrueParser;
import com.github.ivanjermakov.jtrue.exception.SyntaxException;
import com.github.ivanjermakov.jtrue.lang.cst.VisitorConfiguration;
import com.github.ivanjermakov.jtrue.lang.model.ValidationResult;

public class ValidationRuleVisitor<T> implements LangVisitor<ValidationResult> {

	private final JtrueParser.ValidationRuleContext validationRule;
	private final VisitorConfiguration<T> config;

	public ValidationRuleVisitor(JtrueParser.ValidationRuleContext validationRule, VisitorConfiguration<T> config) {
		this.validationRule = validationRule;
		this.config = config;
	}

	@Override
	public ValidationResult visit() {
		if (validationRule.predicate() != null) {
			return new PredicateVisitor<>(validationRule.predicate(), validationRule.errorMessage(), config).visit();
		}

		if (validationRule.group() != null) {
			return new GroupVisitor<>(validationRule.group(), config).visit();
		}

		if (validationRule.fieldRule() != null) {
			return new FieldRuleVisitor<>(validationRule.fieldRule(), config).visit();
		}

		throw new SyntaxException("'validationResult' node must contain one of the nodes: 'predicate', 'group', 'fieldRule'");
	}

}
