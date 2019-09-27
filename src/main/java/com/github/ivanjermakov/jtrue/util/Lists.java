package com.github.ivanjermakov.jtrue.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lists {

	public static <T> List<T> concat(List<T> c1, List<T> c2) {
		return Stream
				.concat(
						c1.stream(),
						c2.stream()
				)
				.collect(Collectors.toList());
	}

}
