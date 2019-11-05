package com.github.ivanjermakov.jtrue.validator;

@FunctionalInterface
public interface Validatable<T> {

	boolean validate(T t);

}
