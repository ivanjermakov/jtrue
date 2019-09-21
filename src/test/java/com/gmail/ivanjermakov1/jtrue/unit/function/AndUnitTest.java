package com.gmail.ivanjermakov1.jtrue.unit.function;

import com.gmail.ivanjermakov1.jtrue.function.And;
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

	@Test
	public void shouldBeFalse2() {
		assertFalse(new And().apply(false, true));
	}

	@Test(expected = NullPointerException.class)
	public void shouldThrowException_WithNull() {
		new And().apply(true, null);
	}

}
