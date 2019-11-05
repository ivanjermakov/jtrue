package com.github.ivanjermakov.jtrue.lang.ast;

import com.github.ivanjermakov.antlr.JtrueLexer;
import com.github.ivanjermakov.antlr.JtrueParser;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class LangParser {

	private static final Logger LOG = LoggerFactory.getLogger(LangParser.class);

	public SyntaxAnalyzer parse(String source) {
		LOG.debug("parse source code: {}", source);

		var inputStream = new ByteArrayInputStream(source.getBytes(StandardCharsets.UTF_8));
		var lexer = new JtrueLexer(fromStream(inputStream));
		var tokenStream = new CommonTokenStream(lexer);
		var parser = new JtrueParser(tokenStream);
		var tree = parser.object();

		LOG.debug("parsed source code successfully");
		LOG.debug("parse tree: {}", tree.toStringTree(parser));

		return new SyntaxAnalyzer(
				lexer,
				parser,
				new BaseErrorListener(),
				tree
		);
	}

	private CharStream fromStream(InputStream is) {
		try {
			return CharStreams.fromStream(is);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
