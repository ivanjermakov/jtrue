package com.gmail.ivanjermakov1.jtrue.core;

import com.gmail.ivanjermakov1.jtrue.exception.InvalidObjectException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Validator<T> implements Validatable<T> {

	private final Throwable baseThrowable;
	private final List<Check<T>> checks;

	public Validator() {
		this(new InvalidObjectException("invalid object"), new ArrayList<>());
	}

	public Validator(Throwable baseThrowable) {
		this(baseThrowable, new ArrayList<>());
	}

	private Validator(Throwable baseThrowable, List<Check<T>> checks) {
		this.baseThrowable = baseThrowable;
		this.checks = checks;
	}

	public <R> PrimitiveValidator<R, T> map(Function<T, R> mapFunction) {
		return new PrimitiveValidator<R, T>(this, mapFunction);
	}

	public Validator<T> check(Predicate<T> predicate) {
		return check(predicate, () -> baseThrowable);
	}

	public Validator<T> check(Predicate<T> predicate, String message) {
		return check(predicate, () -> baseThrowable, () -> message);
	}

	public Validator<T> check(Predicate<T> predicate, Supplier<Throwable> throwableSupplier) {
		return check(predicate, throwableSupplier, () -> throwableSupplier.get().getMessage());
	}

	private Validator<T> check(Predicate<T> predicate, Supplier<Throwable> throwableSupplier, Supplier<String> messageSupplier) {
		checks.add(new Check<>(predicate, throwableSupplier, messageSupplier));
		return this;
	}

	@Override
	public boolean validate(T target) {
		return checks
				.stream()
				.map(c -> c.test(target).getResult())
				.reduce(true, (b1, b2) -> b1 && b2);
	}

	@Override
	public void throwInvalid(T target, Supplier<Throwable> throwableSupplier) throws Throwable {
		if (!validate(target)) throw throwableSupplier.get();
	}

	public List<Throwable> listErrors(T target) {
		return checks
				.stream()
				.map(c -> c.test(target))
				.filter(cc -> !cc.getResult())
				.map(cc -> cc.getCheck().getThrowableSupplier().get())
				.collect(Collectors.toList());
	}

}
