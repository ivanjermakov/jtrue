package com.github.ivanjermakov.jtrue.predicate;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public class EmptyString implements Predicate<String> {

	@Override
	@Contract(pure = true)
	public boolean test(@NotNull String t) {
		return t.isEmpty();
	}

}
