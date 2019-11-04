package com.github.ivanjermakov.jtrue.unit.lang;

import com.github.ivanjermakov.jtrue.lang.LangParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertNotNull;

public class LangParserUnitTest {

	@Test
	public void shouldParseQuery1() throws IOException, URISyntaxException {
		String source = Files.readString(Path.of(LangParser.class.getResource("/lang/query1.true").toURI()));

		ParseTree parse = new LangParser().parse(source);
		assertNotNull(parse);
	}

	@Test
	public void shouldParseQuery2() throws IOException, URISyntaxException {
		String source = Files.readString(Path.of(LangParser.class.getResource("/lang/query2.true").toURI()));

		ParseTree parse = new LangParser().parse(source);
		assertNotNull(parse);
	}

	@Test
	public void shouldParseQuery3() throws IOException, URISyntaxException {
		String source = Files.readString(Path.of(LangParser.class.getResource("/lang/query3.true").toURI()));

		ParseTree parse = new LangParser().parse(source);
		assertNotNull(parse);
	}

	@Test
	public void shouldParseQuery4() throws IOException, URISyntaxException {
		String source = Files.readString(Path.of(LangParser.class.getResource("/lang/query4_trailing_comma.true").toURI()));

		ParseTree parse = new LangParser().parse(source);
		assertNotNull(parse);
	}

}
