package com.gmail.ivanjermakov1.jtrue.unit.predicate;

import com.gmail.ivanjermakov1.jtrue.predicate.IsEmptyString;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IsEmptyStringUnitTest {

	@Test
	public void shouldBeEmpty() {
		assertTrue(new IsEmptyString().test(""));
	}

	@Test
	public void shouldNotBeEmpty() {
		assertFalse(new IsEmptyString().test("str"));
	}

	@Test
	public void shouldNotBeEmptyWithSpace() {
		assertFalse(new IsEmptyString().test(" "));
	}

	@Test
	public void shouldNotBeEmptyWithNull() {
		assertFalse(new IsEmptyString().test(null));
	}

}
