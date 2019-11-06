package com.github.ivanjermakov.jtrue.lang.cst.visitor;

import com.github.ivanjermakov.antlr.JtrueParser;
import com.github.ivanjermakov.jtrue.exception.SyntaxException;
import com.github.ivanjermakov.jtrue.lang.cst.VisitorConfiguration;
import com.github.ivanjermakov.jtrue.lang.model.FieldPath;
import com.github.ivanjermakov.jtrue.lang.model.FieldValue;

public class SingleFieldSelectorVisitor<T> implements LangVisitor<FieldValue> {

	private final JtrueParser.SingleFieldSelectorContext singleFieldSelector;
	private final VisitorConfiguration<T> config;

	public SingleFieldSelectorVisitor(JtrueParser.SingleFieldSelectorContext singleFieldSelector, VisitorConfiguration<T> config) {
		this.singleFieldSelector = singleFieldSelector;
		this.config = config;
	}

	@Override
	public FieldValue visit() throws SyntaxException {
		if (singleFieldSelector.fieldPath() != null) {
			FieldPath fieldPath = new FieldPathVisitor(singleFieldSelector.fieldPath()).visit();
			return fieldPath.query(config.target);
		}

		throw new SyntaxException("'allFieldSelector' node must contain 'fieldPath' node");
	}

}
