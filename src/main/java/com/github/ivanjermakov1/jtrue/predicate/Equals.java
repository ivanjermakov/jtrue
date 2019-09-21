package com.github.ivanjermakov1.jtrue.predicate;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public class Equals<T> implements Predicate<T> {

	private final T to;

	public Equals(T to) {
		this.to = to;
	}

	@Override
	@Contract(pure = true)
	public boolean test(@NotNull T t) {
		return t.equals(to);
	}

}
