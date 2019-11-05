package com.github.ivanjermakov.jtrue.lang.type;

public class Str implements LangType {

	public final String text;

	public Str(String text) {
		this.text = text;
	}

	@Override
	public Object value() {
		return text;
	}

}
