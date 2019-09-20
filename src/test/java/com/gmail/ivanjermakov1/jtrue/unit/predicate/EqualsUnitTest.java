package com.gmail.ivanjermakov1.jtrue.unit.predicate;

import com.gmail.ivanjermakov1.jtrue.predicate.Equals;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EqualsUnitTest {

	@Test
	public void shouldEqual() {
		assertTrue(new Equals<>(2).test(2));
	}

	@Test
	public void shouldNotEqual() {
		assertFalse(new Equals<>(2).test(1));
	}

}
