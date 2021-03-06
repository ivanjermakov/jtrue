package com.github.ivanjermakov.jtrue.validator;

import java.util.function.Predicate;

/**
 * Simple implementation of {@link Validatable} interface.
 * <br><br>
 * All it can do is to {@link #validate(T)} target object by given predicate
 *
 * @param <T> validated object type
 * @see #validate(T)
 */
public class SimpleValidator<T> implements Validatable<T> {

	private final Predicate<T> predicate;

	/**
	 * Instantiate new validator and configure it to validate objects by given predicate.
	 * If predicate returns {@literal true} then object is valid.
	 *
	 * @param predicate predicate to validate objects
	 */
	public SimpleValidator(Predicate<T> predicate) {
		this.predicate = predicate;
	}

	/**
	 * Execute validation on specified target object.
	 *
	 * @param target target object
	 * @return {@literal true} if object is valid, {@literal false} otherwise
	 */
	@Override
	public boolean validate(T target) {
		return predicate.test(target);
	}

}
