package com.github.ivanjermakov.jtrue.lang.cst.visitor;

import com.github.ivanjermakov.antlr.JtrueParser;
import com.github.ivanjermakov.jtrue.lang.cst.VisitorConfiguration;
import com.github.ivanjermakov.jtrue.lang.model.ValidationResult;

import java.util.List;
import java.util.stream.Collectors;

public class ValidationRulesVisitor<T> implements LangVisitor<List<ValidationResult>> {

	private final JtrueParser.ValidationRulesContext validationRules;
	private final VisitorConfiguration<T> config;

	public ValidationRulesVisitor(JtrueParser.ValidationRulesContext validationRules, VisitorConfiguration<T> config) {
		this.validationRules = validationRules;
		this.config = config;
	}

	@Override
	public List<ValidationResult> visit() {
		return this.validationRules.children
				.stream()
				.filter(c -> c instanceof JtrueParser.ValidationRuleContext)
				.map(c -> (JtrueParser.ValidationRuleContext) c)
				.map(vr -> new ValidationRuleVisitor<>(vr, config).visit())
				.collect(Collectors.toList());
	}

}
