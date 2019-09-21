package com.github.ivanjermakov1.jtrue.predicate;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.function.Predicate;

public class EmptyCollection<T> implements Predicate<Collection<? extends T>> {

	@Override
	@Contract(pure = true)
	public boolean test(@NotNull Collection<? extends T> t) {
		return t.isEmpty();
	}

}
