package com.github.ivanjermakov.jtrue.lang.type.parse;

import com.github.ivanjermakov.jtrue.lang.type.Dub;
import com.github.ivanjermakov.jtrue.lang.type.Int;
import com.github.ivanjermakov.jtrue.lang.type.LangTypeParser;
import com.github.ivanjermakov.jtrue.lang.type.Num;

public class NumParser implements LangTypeParser<Num> {

	@Override
	public Num parse(String text) {
		try {
			return new Int(Integer.parseInt(text));
		} catch (NumberFormatException intException) {
			try {
				return new Dub(Double.parseDouble(text));
			} catch (NumberFormatException doubleException) {
				throw new NumberFormatException("unable to parse text to type 'Num'");
			}
		}
	}

}
