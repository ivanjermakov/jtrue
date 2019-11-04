package com.github.ivanjermakov.jtrue.lang;

import com.github.ivanjermakov.antlr.JtrueLexer;
import com.github.ivanjermakov.antlr.JtrueParser;
import org.antlr.v4.runtime.BaseErrorListener;

//TODO: better name
public class SyntaxAnalyzer {

	public final JtrueLexer lexer;
	public final JtrueParser parser;
	public final BaseErrorListener errorListener;
	public final JtrueParser.ObjectContext root;

	public SyntaxAnalyzer(JtrueLexer lexer, JtrueParser parser, BaseErrorListener errorListener, JtrueParser.ObjectContext root) {
		this.lexer = lexer;
		this.parser = parser;
		this.errorListener = errorListener;
		this.root = root;
	}

}
