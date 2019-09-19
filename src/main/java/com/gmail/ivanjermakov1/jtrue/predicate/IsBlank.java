package com.gmail.ivanjermakov1.jtrue.predicate;

import java.util.function.Predicate;

public class IsBlank implements Predicate<String> {

	@Override
	public boolean test(String t) {
		return t.trim().isEmpty();
	}

}
