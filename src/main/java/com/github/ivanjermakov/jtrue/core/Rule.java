package com.github.ivanjermakov.jtrue.core;

import java.util.function.Predicate;

/**
 * Single rule of object verification process.
 * <br><br>
 * It do nothing by itself, because {@link Validator} is lazy-evaluated.
 * {@link Validator} use it's predicate to verify this rule when executing one of Validator's terminal operations.
 * After verifying this rule, calling {@link #test(T)} method, it returns instance of Verified rule, called {@link RuleResult}
 *
 * @param <T> validated object type
 */
class Rule<T> {

	private final Predicate<T> predicate;
	private final String message;

	/**
	 * Create new rule instance
	 *
	 * @param predicate given verification predicate
	 * @param message   error message in case of failed invalid object
	 */
	public Rule(Predicate<T> predicate, String message) {
		this.predicate = predicate;
		this.message = message;
	}

	/**
	 * Apply {@link #predicate} to the specified target object
	 *
	 * @param target target object
	 * @return rule result
	 * @see RuleResult
	 */
	public RuleResult<T> test(T target) {
		return new RuleResult<>(this, predicate.test(target));
	}

	public Predicate<T> getPredicate() {
		return predicate;
	}

	public String getMessage() {
		return message;
	}

}