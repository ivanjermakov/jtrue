package com.github.ivanjermakov.jtrue.test.exception;

import com.github.ivanjermakov.jtrue.exception.SyntaxException;
import org.junit.Test;

public class SyntaxExceptionTest {

	@SuppressWarnings("ThrowableNotThrown")
	@Test
	public void testExceptionCreation() {
		new SyntaxException();
		new SyntaxException("");
		new SyntaxException("", null);
		new SyntaxException((Throwable) null);
	}

}
