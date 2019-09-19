package com.gmail.ivanjermakov1.jtrue.core;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class Check<T> {

	private final Predicate<T> predicate;
	private final Supplier<Throwable> throwableSupplier;
	private final Supplier<String> messageSupplier;

	public Check(Predicate<T> predicate, Supplier<Throwable> throwableSupplier, Supplier<String> messageSupplier) {
		this.predicate = predicate;
		this.throwableSupplier = throwableSupplier;
		this.messageSupplier = messageSupplier;
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

	public Supplier<String> getMessageSupplier() {
		return messageSupplier;
	}

}