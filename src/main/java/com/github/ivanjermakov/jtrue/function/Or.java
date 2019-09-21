package com.github.ivanjermakov.jtrue.function;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.BinaryOperator;

public class Or implements BinaryOperator<Boolean> {

	@Override
	@NotNull
	@Contract(pure = true)
	public Boolean apply(@NotNull Boolean b1, @NotNull Boolean b2) {
		return b1 || b2;
	}

}
