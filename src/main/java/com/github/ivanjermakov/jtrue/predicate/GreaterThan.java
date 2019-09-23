package com.github.ivanjermakov.jtrue.predicate;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public class GreaterThan<T extends Comparable<T>> implements Predicate<T> {

	private final T then;

	public GreaterThan(T then) {
		this.then = then;
	}

	@Override
	@Contract(pure = true)
	public boolean test(@NotNull T t) {
		return t.compareTo(then) > 0;
	}

}
