package com.gmail.ivanjermakov1.jtrue.core;

import java.util.function.Function;
import java.util.function.Predicate;

public class PrimitiveValidator<T, P> {

	private final Validator<P> parent;
	private final Function<P, T> mapFunction;

	public PrimitiveValidator(Validator<P> parent, Function<P, T> mapFunction) {
		this.parent = parent;
		this.mapFunction = mapFunction;
	}

	public Validator<P> check(Predicate<T> predicate) {
		return parent.check(parentPredicate(predicate));
	}

	public Validator<P> check(Predicate<T> predicate, String message) {
		return parent.check(parentPredicate(predicate), message);
	}

	private Predicate<P> parentPredicate(Predicate<T> predicate) {
		return p -> predicate.test(mapFunction.apply(p));
	}

}
