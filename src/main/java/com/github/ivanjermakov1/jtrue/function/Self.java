package com.github.ivanjermakov1.jtrue.function;

import org.jetbrains.annotations.Contract;

import java.util.function.Function;

public class Self<T> implements Function<T, T> {

	@Override
	@Contract(pure = true)
	public T apply(T t) {
		return t;
	}

}
