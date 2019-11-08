package com.github.ivanjermakov.jtrue.test.exception;

import com.github.ivanjermakov.jtrue.exception.InvalidObjectException;
import org.junit.Test;

public class InvalidObjectExceptionTest {

	@SuppressWarnings("ThrowableNotThrown")
	@Test
	public void testExceptionCreation() {
		new InvalidObjectException();
		new InvalidObjectException("");
		new InvalidObjectException("", null);
		new InvalidObjectException((Throwable) null);
	}

}
