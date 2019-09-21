package com.gmail.ivanjermakov1.jtrue.unit.predicate;

import com.gmail.ivanjermakov1.jtrue.predicate.IsEmptyCollection;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IsEmptyCollectionUnitTest {

	@Test
	public void shouldBeEmpty() {
		assertTrue(new IsEmptyCollection<>().test(Collections.emptyList()));
	}

	@Test
	public void shouldNotBeEmpty() {
		assertFalse(new IsEmptyCollection<>().test(Collections.singletonList(new Object())));
	}

}
