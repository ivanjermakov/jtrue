package com.github.ivanjermakov.jtrue.unit.exception;

import com.github.ivanjermakov.jtrue.exception.InvalidObjectException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class InvalidObjectExceptionUnitTest {

	@Test(expected = InvalidObjectException.class)
	public void shouldThrowException_WithoutMessage() throws InvalidObjectException {
		InvalidObjectException exception = new InvalidObjectException();
		assertNull(exception.getMessage());
		throw exception;
	}

	@Test(expected = InvalidObjectException.class)
	public void shouldThrowException_WithMessage() throws InvalidObjectException {
		InvalidObjectException exception = new InvalidObjectException("message");
		assertEquals("message", exception.getMessage());
		throw exception;
	}

}
