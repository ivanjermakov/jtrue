package com.github.ivanjermakov.jtrue.test.predicate;

import com.github.ivanjermakov.jtrue.predicate.NotBlank;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NotBlankUnitTest {

	@Test
	public void shouldNotBeBlank() {
		assertTrue(new NotBlank().test("str"));
	}

	@Test
	public void shouldBeBlank() {
		assertFalse(new NotBlank().test(""));
	}

}
