package com.github.ivanjermakov.jtrue.unit;

import com.github.ivanjermakov.jtrue.core.Validatable;
import com.github.ivanjermakov.jtrue.exception.InvalidObjectException;
import org.junit.Test;

public class ValidatableUnitTest {

	@Test(expected = InvalidObjectException.class)
	public void shouldThrowException_WithDefaultThrowInvalid() throws Throwable {
		Validatable<Object> validatable = target -> false;
		validatable.throwInvalid(new Object());
	}

	@Test
	public void shouldNotThrowException_WithDefaultThrowInvalid() throws Throwable {
		Validatable<Object> validatable = target -> true;
		validatable.throwInvalid(new Object());
	}

}
