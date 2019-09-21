package com.gmail.ivanjermakov1.jtrue.predicate;

import java.util.function.Predicate;

public class Null<T> implements Predicate<T> {

	@Override
	public boolean test(T t) {
		return t == null;
	}

}
