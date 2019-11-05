package com.github.ivanjermakov.jtrue.lang.model;

@FunctionalInterface
public interface ValidationPredicate<T> {

	boolean test(T t, Object... parameters);

}
