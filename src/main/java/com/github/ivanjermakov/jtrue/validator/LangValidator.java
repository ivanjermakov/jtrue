package com.github.ivanjermakov.jtrue.validator;

import com.github.ivanjermakov.jtrue.lang.ast.LangParser;
import com.github.ivanjermakov.jtrue.lang.cst.VisitorConfiguration;
import com.github.ivanjermakov.jtrue.lang.cst.visitor.ObjectVisitor;
import com.github.ivanjermakov.jtrue.lang.model.ValidationError;
import com.github.ivanjermakov.jtrue.lang.model.ValidationPredicate;
import com.github.ivanjermakov.jtrue.lang.model.ValidationResult;

import java.util.Map;

/**
 * Low-level implementation of validator that uses jtrue validation language
 *
 * @param <T> type of object to be validated
 */
public class LangValidator<T> implements Validatable<T> {

	private final String source;
	//	TODO: better system to include predicates into validator
	//	TODO: predefined predicates
	private final Map<String, ValidationPredicate> predicateMap;

	public LangValidator(String source, Map<String, ValidationPredicate> predicateMap) {
		this.source = source;
		this.predicateMap = predicateMap;
	}

	@Override
	public boolean validate(T t) {
		return check(t).isValid;
	}

	public ValidationError error(T t) {
		return check(t).validationError;
	}

	private ValidationResult check(T t) {
		return new ObjectVisitor<>(
				new LangParser().parse(source).root,
				new VisitorConfiguration<>(t, predicateMap)
		).visit();
	}

}
