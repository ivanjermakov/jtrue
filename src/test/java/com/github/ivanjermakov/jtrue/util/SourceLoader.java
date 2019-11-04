package com.github.ivanjermakov.jtrue.util;

import com.github.ivanjermakov.jtrue.lang.LangParser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SourceLoader {

	public static String load(String path) {
		try {
			return Files.readString(Path.of(LangParser.class.getResource(path).toURI()));
		} catch (IOException | URISyntaxException e) {
			throw new RuntimeException("unable to load source", e);
		}
	}

}
