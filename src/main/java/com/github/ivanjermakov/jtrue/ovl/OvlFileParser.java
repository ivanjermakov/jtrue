package com.github.ivanjermakov.jtrue.ovl;

import org.antlr.v4.runtime.tree.ParseTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public class OvlFileParser {

	private static final Logger LOG = LoggerFactory.getLogger(OvlFileParser.class);

	private final OvlParser ovlParser;

	public OvlFileParser(OvlParser ovlParser) {
		this.ovlParser = ovlParser;
	}

	public ParseTree parse(String path) throws IOException, URISyntaxException {
		URL resource = OvlParser.class.getResource(path);
		if (resource == null) throw new IOException("resource \"" + path + "\" not found");

		return ovlParser.parse(Files.readString(Path.of(resource.toURI())));
	}

}
