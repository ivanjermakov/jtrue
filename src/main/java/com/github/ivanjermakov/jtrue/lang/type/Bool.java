package com.github.ivanjermakov.jtrue.lang.type;

public class Bool implements LangType {

	private final boolean value;

	public Bool(boolean value) {
		this.value = value;
	}

	@Override
	public Object value() {
		return value;
	}

}
