package com.github.ivanjermakov.jtrue.test.predicate;

import com.github.ivanjermakov.jtrue.predicate.GreaterThan;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GreaterThanUnitTest {

	@Test
	public void shouldBeTrue() {
		assertTrue(new GreaterThan<>(1).test(2));
	}

	@Test
	public void shouldNotBeTrue() {
		assertFalse(new GreaterThan<>(5).test(2));
	}

}
