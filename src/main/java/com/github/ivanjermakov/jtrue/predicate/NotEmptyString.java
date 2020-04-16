package com.github.ivanjermakov.jtrue.predicate;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public class NotEmptyString implements Predicate<String> {

	@Override
	@Contract(pure = true, value = "null -> fail")
	public boolean test(@NotNull String t) {
		return !t.isEmpty();
	}

}
