package com.github.ivanjermakov.jtrue.test.predicate;

import com.github.ivanjermakov.jtrue.predicate.NotEmptyString;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NotEmptyStringUnitTest {

	@Test
	public void shouldNotBeEmpty() {
		assertTrue(new NotEmptyString().test("str"));
	}

	@Test
	public void shouldBeEmpty() {
		assertFalse(new NotEmptyString().test(""));
	}

	@Test
	public void shouldNotBeEmptyWithSpace() {
		assertTrue(new NotEmptyString().test(" "));
	}

}
