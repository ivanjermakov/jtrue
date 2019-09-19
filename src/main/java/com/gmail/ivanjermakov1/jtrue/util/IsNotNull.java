package com.gmail.ivanjermakov1.jtrue.util;

import java.util.function.Predicate;

public class IsNotNull<T> implements Predicate<T> {

	@Override
	public boolean test(T t) {
		return !new IsNull<T>().test(t);
	}

}
