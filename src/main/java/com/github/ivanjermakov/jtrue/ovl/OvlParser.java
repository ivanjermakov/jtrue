package com.github.ivanjermakov.jtrue.ovl;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class OvlParser {

	private static final Logger LOG = LoggerFactory.getLogger(OvlParser.class);

	public ParseTree parse(String source) throws IOException {
		LOG.debug("parse ovl source code: {}", source);

		InputStream inputStream = new ByteArrayInputStream(source.getBytes(StandardCharsets.UTF_8));
		Lexer lexer = new com.github.ivanjermakov.jtrue.ovl.ovlLexer(CharStreams.fromStream(inputStream));
		TokenStream tokenStream = new CommonTokenStream(lexer);
		com.github.ivanjermakov.jtrue.ovl.ovlParser parser = new com.github.ivanjermakov.jtrue.ovl.ovlParser(tokenStream);
		ParseTree tree = parser.object();

		LOG.debug("parsed ovl source code successfully");
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

		return tree;
	}

}
