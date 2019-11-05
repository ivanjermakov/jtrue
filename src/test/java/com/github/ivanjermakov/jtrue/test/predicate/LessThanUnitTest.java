package com.github.ivanjermakov.jtrue.test.predicate;

import com.github.ivanjermakov.jtrue.predicate.LessThan;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LessThanUnitTest {

	@Test
	public void shouldBeTrue() {
		assertTrue(new LessThan<>(2).test(1));
	}

	@Test
	public void shouldNotBeTrue() {
		assertFalse(new LessThan<>(2).test(5));
	}

}
