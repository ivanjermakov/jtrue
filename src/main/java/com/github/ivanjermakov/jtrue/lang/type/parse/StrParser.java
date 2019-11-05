package com.github.ivanjermakov.jtrue.lang.type.parse;

import com.github.ivanjermakov.jtrue.lang.type.LangTypeParser;
import com.github.ivanjermakov.jtrue.lang.type.Str;

public class StrParser implements LangTypeParser<Str> {

	@Override
	public Str parse(String text) {
		return new Str(text);
	}

}
