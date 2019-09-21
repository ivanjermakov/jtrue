package com.gmail.ivanjermakov1.jtrue.predicate;

import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public class Equals<T> implements Predicate<T> {

	private final T to;

	public Equals(T to) {
		this.to = to;
	}

	@Override
	public boolean test(@NotNull T t) {
		return t.equals(to);
	}

}
