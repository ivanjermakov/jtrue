package com.gmail.ivanjermakov1.jtrue.predicate;

import java.util.function.Predicate;

public class NotNull<T> implements Predicate<T> {

	@Override
	public boolean test(T t) {
		return !new Null<T>().test(t);
	}

}
