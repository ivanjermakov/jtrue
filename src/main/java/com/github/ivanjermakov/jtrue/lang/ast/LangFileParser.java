package com.github.ivanjermakov.jtrue.lang.ast;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LangFileParser {

	private static final Logger LOG = LoggerFactory.getLogger(LangFileParser.class);

	private final LangParser langParser;

	public LangFileParser(LangParser langParser) {
		this.langParser = langParser;
	}

	public SyntaxAnalyzer parse(String path) throws IOException, URISyntaxException {
		URL resource = LangParser.class.getResource(path);
		if (resource == null) throw new IOException("resource \"" + path + "\" not found");

		return langParser.parse(String.join(System.lineSeparator(), Files.readAllLines(Paths.get(resource.toURI()))));
	}

}
