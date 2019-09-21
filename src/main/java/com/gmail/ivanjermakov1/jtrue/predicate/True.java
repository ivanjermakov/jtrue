package com.gmail.ivanjermakov1.jtrue.predicate;

import java.util.function.Predicate;

public class True<T> implements Predicate<T> {

	@Override
	public boolean test(T t) {
		return true;
	}

}
