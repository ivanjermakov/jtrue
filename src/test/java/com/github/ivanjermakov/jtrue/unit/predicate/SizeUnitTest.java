package com.github.ivanjermakov.jtrue.unit.predicate;

import com.github.ivanjermakov.jtrue.predicate.Size;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SizeUnitTest {

	@Test
	public void shouldEqual() {
		assertTrue(new Size(0).test(Collections.emptyList()));
	}

	@Test
	public void shouldNotEqual() {
		assertFalse(new Size(2).test(Arrays.asList(1, 2, 3)));
	}

}
