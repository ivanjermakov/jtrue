package com.github.ivanjermakov.jtrue.test.lang.cst.visitor;

import com.github.ivanjermakov.antlr.JtrueParser;

public class RootVisitor {

	public String visitObject(JtrueParser.ObjectContext ctx) {
		return ctx.getText();
	}

}
