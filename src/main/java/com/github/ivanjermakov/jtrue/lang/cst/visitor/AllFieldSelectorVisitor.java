package com.github.ivanjermakov.jtrue.lang.cst.visitor;

import com.github.ivanjermakov.antlr.JtrueParser;
import com.github.ivanjermakov.jtrue.exception.LangException;
import com.github.ivanjermakov.jtrue.exception.SyntaxException;
import com.github.ivanjermakov.jtrue.lang.cst.VisitorConfiguration;
import com.github.ivanjermakov.jtrue.lang.model.FieldPath;
import com.github.ivanjermakov.jtrue.lang.model.FieldValue;

import java.util.List;
import java.util.stream.Collectors;

public class AllFieldSelectorVisitor<T> implements LangVisitor<List<FieldValue>> {

	private final JtrueParser.AllFieldSelectorContext allFieldSelector;
	private final VisitorConfiguration<T> config;

	public AllFieldSelectorVisitor(JtrueParser.AllFieldSelectorContext allFieldSelector, VisitorConfiguration<T> config) {
		this.allFieldSelector = allFieldSelector;
		this.config = config;
	}

	//	TODO: refactor duplicate
	@Override
	public List<FieldValue> visit() throws SyntaxException {
		if (allFieldSelector.fieldPaths() != null) {
			List<FieldPath> fieldPaths = new FieldPathsVisitor(allFieldSelector.fieldPaths()).visit();

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

		throw new SyntaxException("'allFieldSelector' node must contain 'fieldPath' node");
	}

}
