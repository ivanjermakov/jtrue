package com.github.ivanjermakov1.jtrue.predicate;

import org.jetbrains.annotations.Contract;

import java.util.function.Predicate;

public class False<T> implements Predicate<T> {

	@Override
	@Contract(pure = true)
	public boolean test(T t) {
		return false;
	}

}
