package com.github.ivanjermakov.jtrue.lang.type;

public class Dub extends Num {

	private final double value;

	public Dub(double value) {
		super();
		this.value = value;
	}

	@Override
	public Object value() {
		return value;
	}

}
