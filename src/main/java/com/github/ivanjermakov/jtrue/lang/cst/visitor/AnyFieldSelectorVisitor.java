package com.github.ivanjermakov.jtrue.lang.cst.visitor;

import com.github.ivanjermakov.antlr.JtrueParser;
import com.github.ivanjermakov.jtrue.exception.LangException;
import com.github.ivanjermakov.jtrue.exception.SyntaxException;
import com.github.ivanjermakov.jtrue.lang.cst.VisitorConfiguration;
import com.github.ivanjermakov.jtrue.lang.model.FieldPath;
import com.github.ivanjermakov.jtrue.lang.model.FieldValue;

import java.util.List;
import java.util.stream.Collectors;

public class AnyFieldSelectorVisitor<T> implements LangVisitor<List<FieldValue>> {

	private final JtrueParser.AnyFieldSelectorContext anyFieldSelector;
	private final VisitorConfiguration<T> config;

	public AnyFieldSelectorVisitor(JtrueParser.AnyFieldSelectorContext anyFieldSelector, VisitorConfiguration<T> config) {
		this.anyFieldSelector = anyFieldSelector;
		this.config = config;
	}

	//	TODO: refactor duplicate
	@Override
	public List<FieldValue> visit() throws SyntaxException {
		if (anyFieldSelector.fieldPaths() != null) {
			List<FieldPath> fieldPaths = new FieldPathsVisitor(anyFieldSelector.fieldPaths()).visit();

			return fieldPaths
					.stream()
					.map(fieldPath -> {
								try {
									return fieldPath.query(config.target);
								} catch (NoSuchFieldException | IllegalAccessException e) {
									throw new LangException("unable to query field with path '" + fieldPath + "'", e);
								}
							}
					)
					.collect(Collectors.toList());
		}

		throw new SyntaxException("'anyFieldSelector' node must contain 'fieldPath' node");
	}

}
