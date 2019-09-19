package com.gmail.ivanjermakov1.jtrue.predicate;

import java.util.Collection;
import java.util.function.Predicate;

public class IsEmptyCollection<T> implements Predicate<Collection<? extends T>> {

	@Override
	public boolean test(Collection<? extends T> t) {
		return t.isEmpty();
	}

}
