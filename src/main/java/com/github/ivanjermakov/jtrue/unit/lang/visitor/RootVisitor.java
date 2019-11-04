package com.github.ivanjermakov.jtrue.unit.lang.visitor;

import com.github.ivanjermakov.antlr.JtrueParser;

public class RootVisitor {

	public String visitObject(JtrueParser.ObjectContext ctx) {
		return ctx.getText();
	}

}
