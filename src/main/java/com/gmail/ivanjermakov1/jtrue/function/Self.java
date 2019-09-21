package com.gmail.ivanjermakov1.jtrue.function;

import java.util.function.Function;

public class Self<T> implements Function<T, T> {

	@Override
	public T apply(T t) {
		return t;
	}

}
