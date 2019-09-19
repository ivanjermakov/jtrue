package com.gmail.ivanjermakov1.jtrue.core;

import java.util.function.Supplier;

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
	 * @param target            verification target object
	 * @param throwableSupplier supplier of throwable that will be thrown if target is invalid
	 * @throws Throwable when target is invalid
	 */
	default void throwInvalid(T target, Supplier<Throwable> throwableSupplier) throws Throwable {
		if (!validate(target)) throw throwableSupplier.get();
	}

}
