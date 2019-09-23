package com.github.ivanjermakov.jtrue.predicate;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.function.Predicate;

public class Size implements Predicate<Collection> {

	private final int size;

	public Size(int size) {
		this.size = size;
	}

	@Override
	@Contract(pure = true)
	public boolean test(@NotNull Collection collection) {
		return collection.size() == size;
	}

}
