package com.gmail.ivanjermakov1.jtrue.core;

import com.gmail.ivanjermakov1.jtrue.exception.InvalidObjectException;

/**
 * Parent interface for all object validators
 *
 * @param <T> target object type
 */
@FunctionalInterface
public interface Validatable<T> {

	/**
	 * Checks whether target object is valid or not
	 *
	 * @param target target object
	 * @return true if valid, false if invalid
	 */
	boolean validate(T target);

	/**
	 * Throws {@link Throwable} when target is invalid
	 *
	 * @param target verification target object
	 * @throws Throwable when target is invalid
	 */
	default void throwInvalid(T target) throws Throwable {
		if (!validate(target)) throw new InvalidObjectException("invalid object");
	}

}
