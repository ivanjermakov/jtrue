package com.github.ivanjermakov.jtrue.lang.type.parse;

import com.github.ivanjermakov.jtrue.exception.TypeException;
import com.github.ivanjermakov.jtrue.lang.type.Bool;
import com.github.ivanjermakov.jtrue.lang.type.LangTypeParser;

public class BoolParser implements LangTypeParser<Bool> {

	@Override
	public Bool parse(String text) {
		switch (text) {
			case "true":
				return new Bool(true);
			case "false":
				return new Bool(false);
		}

		throw new TypeException("unable to parse to bool type");
	}

}
