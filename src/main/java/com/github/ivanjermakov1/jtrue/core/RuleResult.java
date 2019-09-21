package com.github.ivanjermakov1.jtrue.core;

/**
 * Describes verified {@link Rule} instance.
 *
 * @param <T> validated object type
 * @see Rule
 */
class RuleResult<T> {

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