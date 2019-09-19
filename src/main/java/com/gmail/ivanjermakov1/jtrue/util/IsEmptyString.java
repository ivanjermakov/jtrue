package com.gmail.ivanjermakov1.jtrue.util;

import java.util.function.Predicate;

public class IsEmptyString implements Predicate<String> {

	@Override
	public boolean test(String t) {
		return t.isEmpty();
	}

}
