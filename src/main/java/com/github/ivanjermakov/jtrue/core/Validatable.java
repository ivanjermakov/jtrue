package com.github.ivanjermakov.jtrue.core;

@FunctionalInterface
public interface Validatable<T> {

	boolean validate(T t);

}
