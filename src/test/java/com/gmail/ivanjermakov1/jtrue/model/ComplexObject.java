package com.gmail.ivanjermakov1.jtrue.model;

public class ComplexObject {

	private final Integer a;
	private final SimpleObject b;

	public ComplexObject(Integer a, SimpleObject b) {
		this.a = a;
		this.b = b;
	}

	public Integer getA() {
		return a;
	}

	public SimpleObject getB() {
		return b;
	}

}
