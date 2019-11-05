package com.github.ivanjermakov.jtrue.validator;

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

	/**
	 * Single rule of object verification process.
	 * <br><br>
	 * It do nothing by itself, because {@link Validator} is lazy-evaluated.
	 * {@link Validator} use it's predicate to verify this rule when executing one of Validator's terminal operations.
	 * After verifying this rule, calling {@link #test(T)} method, it returns instance of Verified rule, called {@link RuleResult}
	 *
	 * @param <T> validated object type
	 */
	public static class Rule<T> implements Predicate<T> {

		private static final String TESTING_ERROR = "exception happened during mapping or testing target field";

		private final Predicate<T> predicate;
		private String message;

		/**
		 * Create new rule instance.
		 *
		 * @param predicate given verification predicate
		 * @param message   error message in case of failed invalid object
		 */
		public Rule(Predicate<T> predicate, String message) {
			this.predicate = predicate;
			this.message = message;
		}

		/**
		 * Apply {@link #predicate} to the specified target object.
		 * If any error happens during testing, false result is returned.
		 *
		 * @param target target object
		 * @return rule result
		 * @see RuleResult
		 */
		public boolean test(T target) {
			try {
				return predicate.test(target);
			} catch (Throwable t) {
				message = TESTING_ERROR + ": " + t;
				return false;
			}
		}

		public Predicate<T> getPredicate() {
			return predicate;
		}

		public String getMessage() {
			return message;
		}

	}

	/**
	 * Describes verified {@link Rule} instance.
	 *
	 * @param <T> validated object type
	 * @see Rule
	 */
	public static class RuleResult<T> {

		private final Rule<T> rule;
		private final boolean result;

		/**
		 * Create new instance
		 *
		 * @param rule   verification rule
		 * @param result result of rule verification
		 */
		public RuleResult(Rule<T> rule, boolean result) {
			this.rule = rule;
			this.result = result;
		}

		public Rule<T> getRule() {
			return rule;
		}

		public boolean getResult() {
			return result;
		}

	}

	private static final String DEFAULT_MESSAGE = "invalid object";
	private static final Function<String, InvalidObjectException> DEFAULT_ERROR = InvalidObjectException::new;
	private static final Function<String, String> DEFAULT_MESSAGE_FUNCTION = m -> DEFAULT_MESSAGE + ": " + m;

	private final List<Rule<T>> rules;
	private final Function<String, String> messageFunction;

	/**
	 * Instantiate new validator.
	 * TODO: example
	 */
	public Validator() {
		this(Collections.emptyList(), DEFAULT_MESSAGE_FUNCTION);
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
				.map(r -> r.test(target))
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
		Validator<F> fieldValidator = validatorFunction.apply(new Validator<F>().message(m -> m));

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
	 * Throws {@link InvalidObjectException} with default error message: {@value DEFAULT_MESSAGE}, when target is invalid.
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
	 * Default message is {@value #DEFAULT_MESSAGE}.
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
		return addRule(predicate, DEFAULT_MESSAGE);
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
