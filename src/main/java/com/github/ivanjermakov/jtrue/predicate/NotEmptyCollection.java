package com.github.ivanjermakov.jtrue.predicate;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.function.Predicate;

public class NotEmptyCollection<T> implements Predicate<Collection<? extends T>> {

	@Override
	@Contract(pure = true)
	public boolean test(@NotNull Collection<? extends T> t) {
		return !t.isEmpty();
	}

}
