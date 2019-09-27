package com.github.ivanjermakov.jtrue.integration;

import com.github.ivanjermakov.jtrue.core.SimpleValidator;
import com.github.ivanjermakov.jtrue.model.EmptyObject;
import com.github.ivanjermakov.jtrue.predicate.False;
import com.github.ivanjermakov.jtrue.predicate.True;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SimpleValidatorIntegrationTest {

	private EmptyObject emptyObject;

	@Before
	public void before() {
		emptyObject = new EmptyObject();
	}

	@Test
	public void shouldValidateEmptyObject() {
		boolean isValid = new SimpleValidator<EmptyObject>(new True<>())
				.validate(emptyObject);

		assertTrue(isValid);
	}

	@Test
	public void shouldNotValidateEmptyObject() {
		boolean isValid = new SimpleValidator<EmptyObject>(new False<>())
				.validate(emptyObject);

		assertFalse(isValid);
	}

}
