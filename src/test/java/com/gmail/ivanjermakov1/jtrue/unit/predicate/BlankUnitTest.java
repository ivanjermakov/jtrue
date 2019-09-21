package com.gmail.ivanjermakov1.jtrue.unit.predicate;

import com.gmail.ivanjermakov1.jtrue.predicate.Blank;
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
