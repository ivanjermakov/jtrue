package com.gmail.ivanjermakov1.jtrue.predicate;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.function.Predicate;

public class IsEmptyCollection<T> implements Predicate<Collection<? extends T>> {

	@Override
	public boolean test(@NotNull Collection<? extends T> t) {
		return t.isEmpty();
	}

}
