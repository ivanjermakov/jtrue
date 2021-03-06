package com.github.ivanjermakov.jtrue.test.predicate;

import com.github.ivanjermakov.jtrue.predicate.Null;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NullUnitTest {

	@Test
	public void shouldEqual() {
		assertTrue(new Null<Integer>().test(null));
	}

	@Test
	public void shouldNotEqual() {
		assertFalse(new Null<Integer>().test(1));
	}

}
