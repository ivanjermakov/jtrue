package com.github.ivanjermakov.jtrue.lang.cst.visitor;

import com.github.ivanjermakov.antlr.JtrueParser;
import com.github.ivanjermakov.jtrue.exception.SyntaxException;
import com.github.ivanjermakov.jtrue.lang.type.LangType;

import java.util.List;
import java.util.stream.Collectors;

public class PredicateParametersVisitor implements LangVisitor<List<LangType>> {

	private final JtrueParser.PredicateParametersContext predicateParameters;

	public PredicateParametersVisitor(JtrueParser.PredicateParametersContext predicateParameters) {
		this.predicateParameters = predicateParameters;
	}

	@Override
	public List<LangType> visit() throws SyntaxException {
		return predicateParameters.children
				.stream()
				.filter(c -> c instanceof JtrueParser.PredicateParameterContext)
				.map(c -> (JtrueParser.PredicateParameterContext) c)
				.map(pp -> new PredicateParameterVisitor(pp).visit())
				.collect(Collectors.toList());
	}

}
