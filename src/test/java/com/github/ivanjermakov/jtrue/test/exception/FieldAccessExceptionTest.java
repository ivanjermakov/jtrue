package com.github.ivanjermakov.jtrue.test.exception;

import com.github.ivanjermakov.jtrue.exception.FieldAccessException;
import org.junit.Test;

public class FieldAccessExceptionTest {

	@SuppressWarnings("ThrowableNotThrown")
	@Test
	public void testExceptionCreation() {
		new FieldAccessException();
		new FieldAccessException("");
		new FieldAccessException("", null);
		new FieldAccessException((Throwable) null);
	}

}
