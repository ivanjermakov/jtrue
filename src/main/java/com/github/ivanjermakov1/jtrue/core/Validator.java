package com.github.ivanjermakov1.jtrue.core;

import com.github.ivanjermakov1.jtrue.exception.InvalidObjectException;
import com.github.ivanjermakov1.jtrue.function.And;
import com.github.ivanjermakov1.jtrue.function.Self;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Main implementation of {@link Validatable}. Used to validate java object instances.
 * <br><br>
 * TODO: write basic usage and tips
 *
 * @param <T> validated object type
 */
public class Validator<T> implements Validatable<T> {

	/**
	 * Class, instance of is that is received with {@link #map(Function)}.
	 * <br><br>
	 * The main difference from the {@link Validator} is that this class has no terminal operations.
	 * It also have useful {@link #use(Validator)} method to compose another validator.
	 * It means that every method of this class is returning additionally configured parent validator.
	 * It is called parent because {@link Validator#map(Function)} returns validator of field type.
	 *
	 * @param <F> Validated field type
	 * @param <P> Parent validator type
	 * @see Validator
	 * @see #map(Function)
	 * @see #use(Validator)
	 */
	public static class FieldValidator<F, P> {

		private final Validator<P> parent;
		private final Function<P, F> mapFunction;

		private FieldValidator(Validator<P> parent, Function<P, F> mapFunction) {
			this.parent = parent;
			this.mapFunction = mapFunction;
		}

		/**
		 * Add new rule to this validator.
		 * Target object is valid if all rule's predicates are true for that object.
		 *
		 * @param predicate validation rule
		 * @return parent validator, configured for that rule
		 */
		public Validator<P> check(Predicate<F> predicate) {
			return parent.addRule(parentPredicate(predicate));
		}

		/**
		 * Add new rule to this validator.
		 * Target object is valid if all rule's predicates are true for that object.
		 *
		 * @param predicate validation rule
		 * @param message   error message for failing this rule.
		 *                  Can be used with terminal operation {@link #listErrors(F)}
		 * @return parent validator, configured for that rule
		 */
		public Validator<P> check(Predicate<F> predicate, String message) {
			return parent.addRule(parentPredicate(predicate), message);
		}

		/**
		 * Configure field validator to use another one to validate current field
		 * TODO: example
		 *
		 * @param validator composed validator
		 * @return parent validator, configured to that field validation configuration
		 */
		public Validator<P> use(Validator<F> validator) {
			return new Validator<>(
					parent.errorSupplier,
					Stream.concat(
							parent.rules
									.stream(),
							validator.rules
									.stream()
									.map(c -> new Rule<P>(
											p -> c.getPredicate().test(mapFunction.apply(p)),
											c.getMessage()))
					)
							.collect(Collectors.toList())
			);
		}

		private Predicate<P> parentPredicate(Predicate<F> predicate) {
			return p -> predicate.test(mapFunction.apply(p));
		}

	}

	private static final String DEFAULT_ERROR_MESSAGE = "invalid object";

	private final Supplier<Throwable> errorSupplier;
	private final List<Rule<T>> rules;

	/**
	 * Instantiate new validator.
	 * TODO: example
	 */
	public Validator() {
		this(() -> new InvalidObjectException(DEFAULT_ERROR_MESSAGE), Collections.emptyList());
	}

	private Validator(Supplier<Throwable> errorSupplier, List<Rule<T>> rules) {
		this.errorSupplier = errorSupplier;
		this.rules = rules;
	}

	/**
	 * Add new rule to this validator.
	 * Target object is valid if all rule's predicates are true for that object.
	 * <br><br>
	 * This is intermediate operation.
	 *
	 * @param predicate validation rule
	 * @return validator, configured for that rule
	 */
	public Validator<T> check(Predicate<T> predicate) {
		return map(new Self<>()).check(predicate);
	}

	/**
	 * Add new rule to this validator.
	 * Target object is valid if all rule's predicates are true for that object.
	 * <br><br>
	 * This is intermediate operation.
	 *
	 * @param predicate validation rule
	 * @param message   error message for failing this rule.
	 *                  Can be used with terminal operation {@link #listErrors(T)}
	 * @return validator, configured for that rule
	 */
	public Validator<T> check(Predicate<T> predicate, String message) {
		return map(new Self<>()).check(predicate, message);
	}

	/**
	 * Map to validate certain field of a target object.
	 * <br><br>
	 * This is intermediate operation.
	 *
	 * @param mapFunction mapping function
	 * @param <F>         field type
	 * @return specific {@link FieldValidator} to validate field next
	 */
	public <F> FieldValidator<F, T> map(Function<T, F> mapFunction) {
		return new FieldValidator<>(this, mapFunction);
	}

	/**
	 * Configure custom exception for failing validation.
	 * <br><br>
	 * Can be used with terminal operation {@link #throwInvalid(T)}
	 * This is intermediate operation.
	 *
	 * @param throwableSupplier supplier of exception to be thrown
	 * @return validator, configured for that exception
	 */
	public Validator<T> throwing(Supplier<Throwable> throwableSupplier) {
		return new Validator<>(throwableSupplier, rules);
	}

	/**
	 * Checks whether target object is valid or not.
	 * Object is valid if none of the {@link RuleResult} result is false.
	 * <br><br>
	 * This is a terminal operation.
	 *
	 * @param target target object
	 * @return {@literal true} if object is valid, {@literal false} otherwise
	 */
	@Override
	public boolean validate(T target) {
		return rules
				.stream()
				.map(c -> c.test(target).getResult())
				.reduce(true, new And());
	}

	/**
	 * Throws {@link Throwable} when target is invalid.
	 * <br><br>
	 * This is a terminal operation.
	 *
	 * @param target verification target object
	 * @throws Throwable when target is invalid
	 */
	@Override
	public void throwInvalid(T target) throws Throwable {
		if (!validate(target)) throw errorSupplier.get();
	}

	/**
	 * List errors from all failed rule checks.
	 * <br><br>
	 * Error message can be specified calling {@link #check(Predicate, String)}.
	 * Default message is {@value #DEFAULT_ERROR_MESSAGE}.
	 * This is a terminal operation.
	 *
	 * @param target verification target object
	 * @return list of error messages
	 */
	public List<String> listErrors(T target) {
		return rules
				.stream()
				.map(c -> c.test(target))
				.filter(cc -> !cc.getResult())
				.map(cc -> cc.getRule().getMessage())
				.collect(Collectors.toList());
	}

	private Validator<T> addRule(Predicate<T> predicate) {
		return addRule(predicate, DEFAULT_ERROR_MESSAGE);
	}

	private Validator<T> addRule(Predicate<T> predicate, String message) {
		return new Validator<>(
				errorSupplier,
				Stream
						.concat(
								rules.stream(),
								Stream.of(new Rule<>(predicate, message))
						)
						.collect(Collectors.toList())
		);
	}

}
