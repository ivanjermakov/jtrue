package com.github.ivanjermakov.jtrue.test.function;

import com.github.ivanjermakov.jtrue.function.Self;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SelfUnitTest {

	@Test
	public void shouldEqual() {
		Object o = new Object();
		assertEquals(o, new Self<>().apply(o));
	}

}
