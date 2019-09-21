package com.gmail.ivanjermakov1.jtrue.core;

import com.gmail.ivanjermakov1.jtrue.exception.InvalidObjectException;
import com.gmail.ivanjermakov1.jtrue.function.And;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Validator<T> implements Validatable<T> {

	public static class PrimitiveValidator<T, P> {

		private final Validator<P> parent;
		private final Function<P, T> mapFunction;

		private PrimitiveValidator(Validator<P> parent, Function<P, T> mapFunction) {
			this.parent = parent;
			this.mapFunction = mapFunction;
		}

		public Validator<P> check(Predicate<T> predicate) {
			return parent.check(parentPredicate(predicate));
		}

		public Validator<P> check(Predicate<T> predicate, String message) {
			return parent.check(parentPredicate(predicate), message);
		}

		public Validator<P> use(Validator<T> b) {
			return new Validator<>(
					parent.getErrorSupplier(),
					Stream.concat(
							parent.checks
									.stream(),
							b.checks
									.stream()
									.map(c -> new Check<P>(
											p -> c.getPredicate().test(mapFunction.apply(p)),
											c.getThrowableSupplier()))
					)
							.collect(Collectors.toList())
			);
		}

		private Predicate<P> parentPredicate(Predicate<T> predicate) {
			return p -> predicate.test(mapFunction.apply(p));
		}

	}

	private final Function<String, Throwable> errorWithMessage = InvalidObjectException::new;
	private final Supplier<Throwable> errorSupplier;
	private final List<Check<T>> checks;

	public Validator() {
		this(() -> new InvalidObjectException("invalid object"), new ArrayList<>());
	}

	private Validator(Supplier<Throwable> errorSupplier, List<Check<T>> checks) {
		this.errorSupplier = errorSupplier;
		this.checks = checks;
	}

	@Override
	public boolean validate(T target) {
		return checks
				.stream()
				.map(c -> c.test(target).getResult())
				.reduce(true, new And());
	}

	@Override
	public void throwInvalid(T target) throws Throwable {
		if (!validate(target)) throw errorSupplier.get();
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
		return new PrimitiveValidator<>(this, mapFunction);
	}

	public Validator<T> throwing(Supplier<Throwable> throwableSupplier) {
		return new Validator<>(throwableSupplier, checks);
	}

	private Supplier<Throwable> getErrorSupplier() {
		return errorSupplier;
	}

	private Validator<T> check(Predicate<T> predicate) {
		return check(predicate, errorSupplier);
	}

	private Validator<T> check(Predicate<T> predicate, String message) {
		return check(predicate, () -> errorWithMessage.apply(message));
	}

	private Validator<T> check(Predicate<T> predicate, Supplier<Throwable> throwableSupplier) {
		checks.add(new Check<>(predicate, throwableSupplier));
		return this;
	}

}
