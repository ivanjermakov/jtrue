package com.gmail.ivanjermakov1.jtrue.predicate;

import java.util.function.Predicate;

public class Equals<T> implements Predicate<T> {

	private final T to;

	public Equals(T to) {
		this.to = to;
	}

	@Override
	public boolean test(T t) {
		return t.equals(to);
	}

}
