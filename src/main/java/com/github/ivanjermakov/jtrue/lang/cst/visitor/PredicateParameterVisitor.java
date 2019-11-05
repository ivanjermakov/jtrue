package com.github.ivanjermakov.jtrue.lang.cst.visitor;

import com.github.ivanjermakov.antlr.JtrueParser;
import com.github.ivanjermakov.jtrue.exception.SyntaxException;
import com.github.ivanjermakov.jtrue.lang.type.LangType;
import com.github.ivanjermakov.jtrue.lang.type.parse.BoolParser;
import com.github.ivanjermakov.jtrue.lang.type.parse.NumParser;
import com.github.ivanjermakov.jtrue.lang.type.parse.StrParser;

public class PredicateParameterVisitor implements LangVisitor<LangType> {

	private final JtrueParser.PredicateParameterContext predicateParameter;

	public PredicateParameterVisitor(JtrueParser.PredicateParameterContext predicateParameter) {
		this.predicateParameter = predicateParameter;
	}

	@Override
	public LangType visit() throws SyntaxException {
		if (predicateParameter.num() != null) {
			return new NumParser().parse(predicateParameter.num().getText());
		}
		if (predicateParameter.str() != null) {
			if (predicateParameter.str().children.size() == 3) {
				return new StrParser().parse(predicateParameter.str().getChild(1).getText());
			}
			throw new SyntaxException("'srt' node must contain 'QUOTE', 'WORD' and 'QUOTE' nodes");
		}
		if (predicateParameter.bool() != null) {
			return new BoolParser().parse(predicateParameter.bool().getText());
		}

		throw new SyntaxException("'predicateParameter' node must contain 'num' node");
	}

}
