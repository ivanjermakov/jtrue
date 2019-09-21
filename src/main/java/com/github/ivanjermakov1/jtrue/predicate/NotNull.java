package com.github.ivanjermakov1.jtrue.predicate;

import org.jetbrains.annotations.Contract;

import java.util.function.Predicate;

public class NotNull<T> implements Predicate<T> {

	@Override
	@Contract(pure = true)
	public boolean test(T t) {
		return !new Null<T>().test(t);
	}

}
