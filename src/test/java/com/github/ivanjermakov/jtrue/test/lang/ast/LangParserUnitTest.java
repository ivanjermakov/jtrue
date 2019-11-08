package com.github.ivanjermakov.jtrue.test.lang.ast;

import com.github.ivanjermakov.jtrue.lang.ast.LangParser;
import com.github.ivanjermakov.jtrue.lang.ast.SyntaxAnalyzer;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;

public class LangParserUnitTest {

	@Test
	public void shouldParseQuery1() throws IOException, URISyntaxException {
		shouldParseQuery("/lang/query1.true");
	}

	@Test
	public void shouldParseQuery2() throws IOException, URISyntaxException {
		shouldParseQuery("/lang/query2.true");
	}

	@Test
	public void shouldParseQuery3() throws IOException, URISyntaxException {
		shouldParseQuery("/lang/query3.true");
	}

	@Test
	public void shouldParseQuery4() throws IOException, URISyntaxException {
		shouldParseQuery("/lang/query4_trailing_comma.true");
	}

	@Test
	public void shouldParseQuery5() throws IOException, URISyntaxException {
		shouldParseQuery("/lang/query5_comments.true");
	}

	private void shouldParseQuery(String path) throws URISyntaxException, IOException {
		String source = Files.readString(Path.of(LangParser.class.getResource(path).toURI()));

		SyntaxAnalyzer analyzer = new LangParser().parse(source);

		assertEquals(0, analyzer.parser.getNumberOfSyntaxErrors());
	}

}
