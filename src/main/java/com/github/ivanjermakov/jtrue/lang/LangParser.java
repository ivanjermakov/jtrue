package com.github.ivanjermakov.jtrue.lang;

import com.github.ivanjermakov.antlr.JtrueLexer;
import com.github.ivanjermakov.antlr.JtrueParser;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class LangParser {

	private static final Logger LOG = LoggerFactory.getLogger(LangParser.class);

	public SyntaxAnalyzer parse(String source) throws IOException {
		LOG.debug("parse source code: {}", source);

		var inputStream = new ByteArrayInputStream(source.getBytes(StandardCharsets.UTF_8));
		var lexer = new JtrueLexer(CharStreams.fromStream(inputStream));
		var tokenStream = new CommonTokenStream(lexer);
		var parser = new JtrueParser(tokenStream);
		var tree = parser.object();

		LOG.debug("parsed source code successfully");
		LOG.debug("parse tree: {}", tree.toStringTree(parser));

//		TODO: move somewhere
//		save parse tree as image
/*		try {
			TreeViewer viewer = new TreeViewer(Arrays.asList(parser.getRuleNames()), tree);
			viewer.setScale(10);
			viewer.save(tree.hashCode() + ".jpg");
		} catch (PrintException e) {
			e.printStackTrace();
		}*/

		return new SyntaxAnalyzer(
				lexer,
				parser,
				new BaseErrorListener(),
				tree
		);
	}

}
