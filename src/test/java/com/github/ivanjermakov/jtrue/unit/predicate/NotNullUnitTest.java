package com.github.ivanjermakov.jtrue.unit.predicate;

import com.github.ivanjermakov.jtrue.predicate.NotNull;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NotNullUnitTest {

	@Test
	public void shouldNotBeNull() {
		assertTrue(new NotNull<Integer>().test(1));
	}

	@Test
	public void shouldBeNull() {
		assertFalse(new NotNull<Integer>().test(null));
	}

}
