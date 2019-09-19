package com.gmail.ivanjermakov1.jtrue.util;

import java.util.function.Predicate;

public class IsNull<T> implements Predicate<T> {

	@Override
	public boolean test(T t) {
		return t == null;
	}

}
