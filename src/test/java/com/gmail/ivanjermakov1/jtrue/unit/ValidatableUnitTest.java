package com.gmail.ivanjermakov1.jtrue.unit;

import com.gmail.ivanjermakov1.jtrue.core.Validatable;
import com.gmail.ivanjermakov1.jtrue.exception.InvalidObjectException;
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
