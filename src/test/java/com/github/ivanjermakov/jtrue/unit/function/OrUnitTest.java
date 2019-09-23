package com.github.ivanjermakov.jtrue.unit.function;

import com.github.ivanjermakov.jtrue.function.Or;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OrUnitTest {

	@Test
	public void shouldBeFalse() {
		assertFalse(new Or().apply(false, false));
	}

	@Test
	public void shouldBeTrue() {
		assertTrue(new Or().apply(false, true));
	}

	@Test
	public void shouldBeTrue2() {
		assertTrue(new Or().apply(true, true));
	}

}
