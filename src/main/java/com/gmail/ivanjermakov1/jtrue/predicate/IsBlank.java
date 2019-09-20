package com.gmail.ivanjermakov1.jtrue.predicate;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public class IsBlank implements Predicate<String> {

	@Override
	@Contract("null -> false")
	public boolean test(@NotNull String t) {
		return t != null && t.trim().isEmpty();
	}

}
