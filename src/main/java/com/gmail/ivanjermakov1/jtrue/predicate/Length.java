package com.gmail.ivanjermakov1.jtrue.predicate;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public class Length implements Predicate<String> {

	private final int length;

	public Length(int length) {
		this.length = length;
	}

	@Override
	@Contract(pure = true)
	public boolean test(@NotNull String s) {
		return s.length() == length;
	}

}
