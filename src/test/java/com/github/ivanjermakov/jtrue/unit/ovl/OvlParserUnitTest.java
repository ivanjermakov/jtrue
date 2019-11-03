package com.github.ivanjermakov.jtrue.unit.ovl;

import com.github.ivanjermakov.jtrue.ovl.OvlParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertNotNull;

public class OvlParserUnitTest {

	@Test
	public void shouldParseQuery1() throws IOException, URISyntaxException {
		String source = Files.readString(Path.of(OvlParser.class.getResource("/ovl/query1.ovl").toURI()));

		ParseTree parse = new OvlParser().parse(source);
		assertNotNull(parse);
	}

	@Test
	public void shouldParseQuery2() throws IOException, URISyntaxException {
		String source = Files.readString(Path.of(OvlParser.class.getResource("/ovl/query2.ovl").toURI()));

		ParseTree parse = new OvlParser().parse(source);
		assertNotNull(parse);
	}

	@Test
	public void shouldParseQuery3() throws IOException, URISyntaxException {
		String source = Files.readString(Path.of(OvlParser.class.getResource("/ovl/query3.ovl").toURI()));

		ParseTree parse = new OvlParser().parse(source);
		assertNotNull(parse);
	}

	@Test
	public void shouldParseQuery4() throws IOException, URISyntaxException {
		String source = Files.readString(Path.of(OvlParser.class.getResource("/ovl/query4_trailing_comma.ovl").toURI()));

		ParseTree parse = new OvlParser().parse(source);
		assertNotNull(parse);
	}

}
