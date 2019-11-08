package com.github.ivanjermakov.jtrue.lang.cst.visitor;

import com.github.ivanjermakov.antlr.JtrueParser;
import com.github.ivanjermakov.jtrue.exception.SyntaxException;
import org.antlr.v4.runtime.misc.Interval;

public class StrVisitor implements LangVisitor<String> {

	private final JtrueParser.StrContext str;

	public StrVisitor(JtrueParser.StrContext str) {
		this.str = str;
	}

	/**
	 * Guide: https://stackoverflow.com/a/26533266/8662097
	 *
	 * @return
	 * @throws SyntaxException
	 */
	@Override
	public String visit() throws SyntaxException {
		if (str.start == null || str.stop == null || str.start.getStartIndex() < 0 || str.stop.getStopIndex() < 0)
			return str.getText();

		String full = str.start.getInputStream().getText(Interval.of(str.start.getStartIndex(), str.stop.getStopIndex()));
		return full.substring(1, full.length() - 1);
	}

}
