package com.github.ivanjermakov.jtrue.test.predicate;

import com.github.ivanjermakov.jtrue.predicate.Length;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LengthUnitTest {

	@Test
	public void shouldEqual() {
		assertTrue(new Length(0).test(""));
	}

	@Test
	public void shouldNotEqual() {
		assertFalse(new Length(2).test("abc"));
	}

}
