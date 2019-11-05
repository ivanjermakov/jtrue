package com.github.ivanjermakov.jtrue.lang.type;

public class Int extends Num {

	private final int value;

	public Int(int value) {
		super();
		this.value = value;
	}

	@Override
	public Object value() {
		return value;
	}

}
