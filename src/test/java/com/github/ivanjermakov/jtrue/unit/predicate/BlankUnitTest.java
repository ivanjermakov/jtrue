package com.github.ivanjermakov.jtrue.unit.predicate;

import com.github.ivanjermakov.jtrue.predicate.Blank;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BlankUnitTest {

	@Test
	public void shouldBeBlank() {
		assertTrue(new Blank().test(""));
	}

	@Test
	public void shouldNotBeBlank() {
		assertFalse(new Blank().test("str"));
	}

}
