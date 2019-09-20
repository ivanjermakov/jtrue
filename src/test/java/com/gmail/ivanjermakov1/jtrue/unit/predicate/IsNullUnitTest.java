package com.gmail.ivanjermakov1.jtrue.unit.predicate;

import com.gmail.ivanjermakov1.jtrue.predicate.IsNull;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IsNullUnitTest {

	@Test
	public void shouldEqual() {
		assertTrue(new IsNull<Integer>().test(null));
	}

	@Test
	public void shouldNotEqual() {
		assertFalse(new IsNull<Integer>().test(1));
	}

}
