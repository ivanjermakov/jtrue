package com.github.ivanjermakov.jtrue.unit.lang;

import com.github.ivanjermakov.jtrue.lang.LangFileParser;
import com.github.ivanjermakov.jtrue.lang.LangParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.assertNotNull;

public class LangFileParserUnitTest {

	@Test
	public void shouldParseQuery1() throws IOException, URISyntaxException {
		ParseTree parse = new LangFileParser(new LangParser()).parse("/lang/query1.true").root;
		assertNotNull(parse);
	}

	@Test(expected = IOException.class)
	public void shouldThrowException_WhenFileNotFound() throws IOException, URISyntaxException {
		new LangFileParser(new LangParser()).parse("/lang/wrong_filename.abc");
	}

}
