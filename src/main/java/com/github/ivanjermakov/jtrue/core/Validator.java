package com.github.ivanjermakov.jtrue.core;

import com.github.ivanjermakov.jtrue.exception.InvalidObjectException;
import com.github.ivanjermakov.jtrue.function.And;
import com.github.ivanjermakov.jtrue.util.Lists;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

//TODO: AND/OR strategy for rules

/**
 * Main implementation of {@link Validatable}. Used to validate java object instances.
 * <br><br>
 * TODO: write basic usage and tips
 *
 * @param <T> validated object type
 */
public class Validator<T> implements Validatable<T> {

	private static final String DEFAULT_ERROR_MESSAGE = "invalid object";
	private static final Function<String, InvalidObjectException> DEFAULT_ERROR = InvalidObjectException::new;

	private final List<Rule<T>> rules;
	private final Function<String, String> messageFunction;

	/**
	 * Instantiate new validator.
	 * TODO: example
	 */
	public Validator() {
		this(Collections.emptyList(), m -> m);
	}

	private Validator(List<Rule<T>> rules, Function<String, String> messageFunction) {
		this.rules = rules;
		this.messageFunction = messageFunction;
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
				.map(c -> c.test(target))
				.reduce(true, new And());
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
	public Validator<T> rule(@NotNull Predicate<T> predicate) {
		return addRule(predicate);
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
	public Validator<T> rule(@NotNull Predicate<T> predicate, @NotNull String message) {
		return addRule(predicate, message);
	}

	/**
	 * Validate certain field of a target object.
	 * <br><br>
	 * This is intermediate operation.
	 *
	 * @param mapFunction       mapping function, returns field value
	 * @param validatorFunction function that passes new {@literal new Validator<F>()} for further field validation.
	 *                          Must receive configured validator back. TODO: example
	 * @param <F>               field type
	 * @return {@link Validator}, configured to validate specified field
	 */
	public <F> Validator<T> field(@NotNull Function<T, F> mapFunction, @NotNull Function<Validator<F>, Validator<F>> validatorFunction) {
//		TODO: deal with NPE in mapFunction
		Validator<F> fieldValidator = validatorFunction.apply(new Validator<>());

		return new Validator<>(
				Lists.concat(
						rules,
						fieldValidator.rules
								.stream()
								.map(r -> new Rule<T>(
												t -> r.getPredicate().test(mapFunction.apply(t)),
										fieldValidator.messageFunction.apply(r.getMessage())
										)
								)
								.collect(Collectors.toList())
				),
				messageFunction
		);
	}

	/**
	 * Throws {@link InvalidObjectException} with default error message: {@value DEFAULT_ERROR_MESSAGE}, when target is invalid.
	 * <br><br>
	 * This is a terminal operation.
	 *
	 * @param target verification target object
	 * @throws InvalidObjectException when target is invalid
	 */
	public void throwInvalid(T target) throws InvalidObjectException {
		if (!validate(target)) throw DEFAULT_ERROR.apply(formatErrorList());
	}

	/**
	 * Throws specified exception when target is invalid.
	 * <br><br>
	 * This is a terminal operation.
	 *
	 * @param target        verification target object
	 * @param errorSupplier supplier of exception to be thrown
	 * @throws E when target is invalid
	 */
	public <E extends Throwable> void throwInvalid(T target, @NotNull Supplier<E> errorSupplier) throws E {
		if (!validate(target)) throw errorSupplier.get();
	}

	/**
	 * Throws specified exception with formatted message when target is invalid.
	 * <br><br>
	 * This is a terminal operation.
	 *
	 * @param target        verification target object
	 * @param errorSupplier supplier of exception to be thrown. {@link String} argument represents error message,
	 *                      generated from {@link #listErrors(T)} method
	 * @throws E when target is invalid
	 */
	public <E extends Throwable> void throwInvalid(T target, @NotNull Function<String, E> errorSupplier) throws E {
		if (!validate(target)) throw errorSupplier.apply(formatErrorList());
	}

	/**
	 * List errors from all failed rule checks.
	 * <br><br>
	 * Error message can be specified calling {@link #rule(Predicate, String)}.
	 * Default message is {@value #DEFAULT_ERROR_MESSAGE}.
	 * This is a terminal operation.
	 *
	 * @param target verification target object
	 * @return list of error messages
	 */
	public List<String> listErrors(T target) {
		return rules
				.stream()
				.map(r -> new RuleResult<>(r, r.test(target)))
				.filter(cc -> !cc.getResult())
				.map(cc -> cc.getRule().getMessage())
				.collect(Collectors.toList());
	}

	public Validator<T> message(Function<String, String> messageFunction) {
		return new Validator<>(rules, messageFunction);
	}

	private Validator<T> addRule(Predicate<T> predicate) {
		return addRule(predicate, DEFAULT_ERROR_MESSAGE);
	}

	private Validator<T> addRule(Predicate<T> predicate, String message) {
		return new Validator<>(
				Lists.concat(
						rules,
						Collections.singletonList(new Rule<>(predicate, message))
				),
				messageFunction
		);
	}

	private String formatErrorList() {
		return messageFunction.apply(rules
				.stream()
				.map(Rule::getMessage)
				.collect(Collectors.joining(", "))
				.concat(";")
		);
	}

}
