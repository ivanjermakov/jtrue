package com.gmail.ivanjermakov1.jtrue.predicate;

import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public class IsEmptyString implements Predicate<String> {

	@Override
	public boolean test(@NotNull String t) {
		return t.isEmpty();
	}

}
