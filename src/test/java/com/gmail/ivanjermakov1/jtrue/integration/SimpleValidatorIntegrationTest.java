package com.gmail.ivanjermakov1.jtrue.integration;

import com.gmail.ivanjermakov1.jtrue.core.SimpleValidator;
import com.gmail.ivanjermakov1.jtrue.exception.InvalidObjectException;
import com.gmail.ivanjermakov1.jtrue.model.EmptyObject;
import com.gmail.ivanjermakov1.jtrue.predicate.False;
import com.gmail.ivanjermakov1.jtrue.predicate.True;
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

	@Test(expected = InvalidObjectException.class)
	public void shouldThrowException_WhenNotValidateEmptyObject() throws Throwable {
		new SimpleValidator<EmptyObject>(new False<>())
				.throwInvalid(emptyObject);
	}

}
