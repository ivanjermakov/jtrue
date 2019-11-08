package com.github.ivanjermakov.jtrue.test.exception;

import com.github.ivanjermakov.jtrue.exception.LangException;
import org.junit.Test;

public class LangExceptionTest {

	@SuppressWarnings("ThrowableNotThrown")
	@Test
	public void testExceptionCreation() {
		new LangException();
		new LangException("");
		new LangException("", null);
		new LangException((Throwable) null);
	}

}
