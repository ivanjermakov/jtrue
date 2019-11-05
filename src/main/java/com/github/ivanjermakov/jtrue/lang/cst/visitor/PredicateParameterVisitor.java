package com.github.ivanjermakov.jtrue.lang.cst.visitor;

import com.github.ivanjermakov.antlr.JtrueParser;
import com.github.ivanjermakov.jtrue.exception.SyntaxException;
import com.github.ivanjermakov.jtrue.lang.type.LangType;
import com.github.ivanjermakov.jtrue.lang.type.Str;
import com.github.ivanjermakov.jtrue.lang.type.parse.BoolParser;
import com.github.ivanjermakov.jtrue.lang.type.parse.NumParser;
import com.github.ivanjermakov.jtrue.lang.type.parse.StrParser;
import org.antlr.v4.runtime.misc.Interval;

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
			String string = new StrParser().parse(getFullText(predicateParameter.str())).text;
			return new Str(string.substring(1, string.length() - 1));
		}
		if (predicateParameter.bool() != null) {
			return new BoolParser().parse(predicateParameter.bool().getText());
		}

		throw new SyntaxException("'predicateParameter' node must contain 'num' node");
	}

	/**
	 * Guide: https://stackoverflow.com/a/26533266/8662097
	 *
	 * @param str
	 * @return
	 */
	private String getFullText(JtrueParser.StrContext str) {
		if (str.start == null || str.stop == null || str.start.getStartIndex() < 0 || str.stop.getStopIndex() < 0)
			return str.getText();

		return str.start.getInputStream().getText(Interval.of(str.start.getStartIndex(), str.stop.getStopIndex()));
	}

}
