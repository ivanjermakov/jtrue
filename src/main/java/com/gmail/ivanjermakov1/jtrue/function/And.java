package com.gmail.ivanjermakov1.jtrue.function;

import org.jetbrains.annotations.NotNull;

import java.util.function.BinaryOperator;

public class And implements BinaryOperator<Boolean> {

	@Override
	@NotNull
	public Boolean apply(@NotNull Boolean b1, @NotNull Boolean b2) {
		return b1 && b2;
	}

}
