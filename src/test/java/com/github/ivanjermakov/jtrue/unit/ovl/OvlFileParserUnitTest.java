package com.github.ivanjermakov.jtrue.unit.ovl;

import com.github.ivanjermakov.jtrue.ovl.OvlFileParser;
import com.github.ivanjermakov.jtrue.ovl.OvlParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.assertNotNull;

public class OvlFileParserUnitTest {

	@Test
	public void shouldParseQuery1() throws IOException, URISyntaxException {
		ParseTree parse = new OvlFileParser(new OvlParser()).parse("/ovl/query1.ovl");
		assertNotNull(parse);
	}

	@Test(expected = IOException.class)
	public void shouldThrowException_WhenFileNotFound() throws IOException, URISyntaxException {
		new OvlFileParser(new OvlParser()).parse("/ovl/wrong_filename.abc");
	}

}
