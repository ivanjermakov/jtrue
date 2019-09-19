package com.gmail.ivanjermakov1.jtrue.core;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class Check<T> {

	private final Predicate<T> predicate;
	private final Supplier<Throwable> throwableSupplier;

	public Check(Predicate<T> predicate, Supplier<Throwable> throwableSupplier) {
		this.predicate = predicate;
		this.throwableSupplier = throwableSupplier;
	}

	public CompleteCheck<T> test(T target) {
		return new CompleteCheck<>(this, predicate.test(target));
	}

	public Predicate<T> getPredicate() {
		return predicate;
	}

	public Supplier<Throwable> getThrowableSupplier() {
		return throwableSupplier;
	}

}