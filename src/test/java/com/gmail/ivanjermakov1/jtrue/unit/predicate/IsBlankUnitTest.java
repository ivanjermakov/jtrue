package com.gmail.ivanjermakov1.jtrue.unit.predicate;

import com.gmail.ivanjermakov1.jtrue.predicate.IsBlank;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IsBlankUnitTest {

	@Test
	public void shouldBeBlank() {
		assertTrue(new IsBlank().test(""));
	}

	@Test
	public void shouldNotBeBlank() {
		assertFalse(new IsBlank().test("str"));
	}

}
