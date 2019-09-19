package com.gmail.ivanjermakov1.jtrue.core;

import com.gmail.ivanjermakov1.jtrue.exception.InvalidObjectException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Validator<T> implements Validatable<T> {

	private static final Throwable DEFAULT_ERROR = new InvalidObjectException("invalid object");

	private final List<Check<T>> checks;

	public Validator() {
		this(new ArrayList<>());
	}

	private Validator(List<Check<T>> checks) {
		this.checks = checks;
	}

	@Override
	public boolean validate(T target) {
		return checks
				.stream()
				.map(c -> c.test(target).getResult())
				.reduce(true, (b1, b2) -> b1 && b2);
	}

	@Override
	public void throwInvalid(T target) throws Throwable {
		if (!validate(target)) throw DEFAULT_ERROR;
	}

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

	public <R> PrimitiveValidator<R, T> map(Function<T, R> mapFunction) {
		return new PrimitiveValidator<R, T>(this, mapFunction);
	}

	Validator<T> check(Predicate<T> predicate) {
		return check(predicate, () -> DEFAULT_ERROR);
	}

	Validator<T> check(Predicate<T> predicate, String message) {
		return check(predicate, () -> defaultErrorWithMessage(message));
	}

	private Validator<T> check(Predicate<T> predicate, Supplier<Throwable> throwableSupplier) {
		checks.add(new Check<>(predicate, throwableSupplier));
		return this;
	}

	private Throwable defaultErrorWithMessage(String message) {
		return new InvalidObjectException(message);
	}

}
