package com.github.ivanjermakov.jtrue.function;

import org.jetbrains.annotations.Contract;

import java.util.function.UnaryOperator;

public class Self<T> implements UnaryOperator<T> {

	@Override
	@Contract(pure = true)
	public T apply(T t) {
		return t;
	}

}
