package com.github.ivanjermakov.jtrue.lang.cst.visitor;

import com.github.ivanjermakov.antlr.JtrueParser;
import com.github.ivanjermakov.jtrue.exception.SyntaxException;
import com.github.ivanjermakov.jtrue.lang.cst.VisitorConfiguration;
import com.github.ivanjermakov.jtrue.lang.model.ValidationResult;

public class ObjectVisitor<T> implements LangVisitor<ValidationResult> {

	private final JtrueParser.ObjectContext object;
	private final VisitorConfiguration<T> config;

	public ObjectVisitor(JtrueParser.ObjectContext object, VisitorConfiguration<T> config) {
		this.object = object;
		this.config = config;
	}

	@Override
	public ValidationResult visit() {
		if (object.negate() != null) {
			return new NegateVisitor<>(object.negate(), config).visit();
		}
		if (object.group() != null) {
			return new GroupVisitor<>(object.group(), config).visit();
		}

		throw new SyntaxException("'object' node must contain 'group' or 'negate' nodes");
	}

}
