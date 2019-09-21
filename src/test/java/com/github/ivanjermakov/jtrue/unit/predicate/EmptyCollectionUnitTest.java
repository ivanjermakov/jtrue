package com.github.ivanjermakov.jtrue.unit.predicate;

import com.github.ivanjermakov.jtrue.predicate.EmptyCollection;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EmptyCollectionUnitTest {

	@Test
	public void shouldBeEmpty() {
		assertTrue(new EmptyCollection<>().test(Collections.emptyList()));
	}

	@Test
	public void shouldNotBeEmpty() {
		assertFalse(new EmptyCollection<>().test(Collections.singletonList(new Object())));
	}

}
