package com.github.ivanjermakov.jtrue.predicate;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.function.Predicate;

public class NotEmptyCollection implements Predicate<Collection<?>> {

	@Override
	@Contract(pure = true, value = "null -> fail")
	public boolean test(@NotNull Collection<?> c) {
		return !c.isEmpty();
	}

}
