package com.gmail.ivanjermakov1.jtrue.core;

public class CompleteCheck<T> {

	private final Check<T> check;
	private final boolean result;

	public CompleteCheck(Check<T> check, boolean result) {
		this.check = check;
		this.result = result;
	}

	public Check<T> getCheck() {
		return check;
	}

	public boolean getResult() {
		return result;
	}

}