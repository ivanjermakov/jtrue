package com.gmail.ivanjermakov1.jtrue.core;

public class CompleteCheck<T> {

	final Check<T> check;
	final boolean result;

	CompleteCheck(Check<T> check, boolean result) {
		this.check = check;
		this.result = result;
	}

}