package com.github.ivanjermakov.jtrue.lang.cst.visitor;

import com.github.ivanjermakov.antlr.JtrueParser;
import com.github.ivanjermakov.jtrue.exception.SyntaxException;
import com.github.ivanjermakov.jtrue.lang.cst.VisitorConfiguration;
import com.github.ivanjermakov.jtrue.lang.model.ValidationResult;

public class FieldRuleVisitor<T> implements LangVisitor<ValidationResult> {

	private final JtrueParser.FieldRuleContext fieldRuleContext;
	private final VisitorConfiguration<T> config;

	public FieldRuleVisitor(JtrueParser.FieldRuleContext fieldRuleContext, VisitorConfiguration<T> config) {
		this.fieldRuleContext = fieldRuleContext;
		this.config = config;
	}

	@Override
	public ValidationResult visit() throws SyntaxException {
		if (fieldRuleContext.fieldSelector() != null && fieldRuleContext.object() != null) {
			return new FieldSelectorVisitor<>(fieldRuleContext.fieldSelector(), fieldRuleContext.object(), config).visit();
		}

		throw new SyntaxException("'fieldRule' node must contain 'fieldSelector' and 'object' nodes");
	}

}
