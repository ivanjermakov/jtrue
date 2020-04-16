package com.github.ivanjermakov.jtrue.test.predicate;

import com.github.ivanjermakov.jtrue.predicate.NotEmptyCollection;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NotEmptyCollectionUnitTest {

	@Test
	public void shouldNotBeEmpty() {
		assertTrue(new NotEmptyCollection().test(Collections.singletonList(new Object())));
	}

	@Test
	public void shouldBeEmpty() {
		assertFalse(new NotEmptyCollection().test(Collections.emptyList()));
	}

}
