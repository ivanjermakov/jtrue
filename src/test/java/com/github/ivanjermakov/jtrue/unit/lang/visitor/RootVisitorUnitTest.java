package com.github.ivanjermakov.jtrue.unit.lang.visitor;

import com.github.ivanjermakov.jtrue.lang.LangParser;
import com.github.ivanjermakov.jtrue.lang.SyntaxAnalyzer;
import com.github.ivanjermakov.jtrue.util.SourceLoader;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class RootVisitorUnitTest {

	@Test
	public void test() throws IOException {
		//	TODO: move to test utils
		String source = SourceLoader.load("/lang/query1.true");

		SyntaxAnalyzer analyzer = new LangParser().parse(source);
		String out = new RootVisitor().visitObject(analyzer.root);
		System.out.println("out: " + out);
		assertEquals(analyzer.root.getText(), out);
	}
}
