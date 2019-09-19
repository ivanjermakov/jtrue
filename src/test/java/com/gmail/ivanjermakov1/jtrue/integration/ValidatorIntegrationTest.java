package com.gmail.ivanjermakov1.jtrue.integration;

import com.gmail.ivanjermakov1.jtrue.core.Validator;
import com.gmail.ivanjermakov1.jtrue.model.SimpleObject;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ValidatorIntegrationTest {

	@Test
	public void shouldValidateEmptyObject() {
		SimpleObject simpleObject = new SimpleObject();

		boolean isValid = new Validator<SimpleObject>()
				.isValid(simpleObject);

		assertTrue(isValid);
	}

}
