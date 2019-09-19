package com.gmail.ivanjermakov1.jtrue.core;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class Check<T> {

	final Predicate<T> predicate;
	final Supplier<Throwable> throwableSupplier;
	final Supplier<String> messageSupplier;

	Check(Predicate<T> predicate, Supplier<Throwable> throwableSupplier, Supplier<String> messageSupplier) {
		this.predicate = predicate;
		this.throwableSupplier = throwableSupplier;
		this.messageSupplier = messageSupplier;
	}

	CompleteCheck<T> test(T target) {
		return new CompleteCheck<>(this, predicate.test(target));
	}

}