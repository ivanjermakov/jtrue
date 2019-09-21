package com.gmail.ivanjermakov1.jtrue.unit.predicate;

import com.gmail.ivanjermakov1.jtrue.predicate.Null;
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
