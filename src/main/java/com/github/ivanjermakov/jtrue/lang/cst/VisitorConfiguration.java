package com.github.ivanjermakov.jtrue.lang.cst;

import com.github.ivanjermakov.jtrue.lang.model.ValidationPredicate;

import java.util.Map;

public class VisitorConfiguration<T> {

	public final T target;
	public final Map<String, ValidationPredicate> predicateMap;

	public VisitorConfiguration(T target, Map<String, ValidationPredicate> predicateMap) {
		this.target = target;
		this.predicateMap = predicateMap;
	}

	public <R> VisitorConfiguration<R> withTarget(R target) {
		return new VisitorConfiguration<>(target, predicateMap);
	}

}
