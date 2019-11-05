package com.github.ivanjermakov.jtrue.test.function;

import com.github.ivanjermakov.jtrue.function.And;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AndUnitTest {

	@Test
	public void shouldBeTrue() {
		assertTrue(new And().apply(true, true));
	}

	@Test
	public void shouldBeFalse() {
		assertFalse(new And().apply(true, false));
	}

}
