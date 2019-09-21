package com.gmail.ivanjermakov1.jtrue.predicate;

import org.jetbrains.annotations.Contract;

import java.util.function.Predicate;

public class True<T> implements Predicate<T> {

	@Override
	@Contract(pure = true)
	public boolean test(T t) {
		return true;
	}

}
