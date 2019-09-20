package com.gmail.ivanjermakov1.jtrue.unit.predicate;

import com.gmail.ivanjermakov1.jtrue.predicate.IsNotNull;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IsNotNullUnitTest {

	@Test
	public void shouldNotBeNull() {
		assertTrue(new IsNotNull<Integer>().test(1));
	}

	@Test
	public void shouldBeNull() {
		assertFalse(new IsNotNull<Integer>().test(null));
	}

}
