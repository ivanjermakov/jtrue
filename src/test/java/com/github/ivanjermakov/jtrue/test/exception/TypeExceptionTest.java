package com.github.ivanjermakov.jtrue.test.exception;

import com.github.ivanjermakov.jtrue.exception.TypeException;
import org.junit.Test;

public class TypeExceptionTest {

	@SuppressWarnings("ThrowableNotThrown")
	@Test
	public void testExceptionCreation() {
		new TypeException();
		new TypeException("");
		new TypeException("", null);
		new TypeException((Throwable) null);
	}

}
