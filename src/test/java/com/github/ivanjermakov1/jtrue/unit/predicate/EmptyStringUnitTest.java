package com.github.ivanjermakov1.jtrue.unit.predicate;

import com.github.ivanjermakov1.jtrue.predicate.EmptyString;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EmptyStringUnitTest {

	@Test
	public void shouldBeEmpty() {
		assertTrue(new EmptyString().test(""));
	}

	@Test
	public void shouldNotBeEmpty() {
		assertFalse(new EmptyString().test("str"));
	}

	@Test
	public void shouldNotBeEmptyWithSpace() {
		assertFalse(new EmptyString().test(" "));
	}

}
