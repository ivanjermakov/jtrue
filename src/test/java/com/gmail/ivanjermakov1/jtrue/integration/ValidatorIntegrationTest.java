package com.gmail.ivanjermakov1.jtrue.integration;

import com.gmail.ivanjermakov1.jtrue.core.Validator;
import com.gmail.ivanjermakov1.jtrue.model.EmptyObject;
import com.gmail.ivanjermakov1.jtrue.model.SimpleObject;
import com.gmail.ivanjermakov1.jtrue.util.Equals;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ValidatorIntegrationTest {

	@Test
	public void shouldValidateEmptyObject() {
		EmptyObject emptyObject = new EmptyObject();

		boolean isValid = new Validator<EmptyObject>()
				.validate(emptyObject);

		assertTrue(isValid);
	}

	@Test
	public void shouldValidateSimpleObjectWithoutChecks() {
		SimpleObject simpleObject = new SimpleObject();

		boolean isValid = new Validator<SimpleObject>()
				.validate(simpleObject);

		assertTrue(isValid);
	}

	@Test
	public void shouldValidateSimpleObjectWithoutMap() {
		SimpleObject simpleObject = new SimpleObject(1, 'a');

		Validator<SimpleObject> check = new Validator<SimpleObject>()
				.check(o -> new Equals<>(1).test(o.a));
		boolean isValid = check
				.validate(simpleObject);

		assertTrue(isValid);
	}

	@Test
	public void shouldValidateSimpleObjectWithMap() {
		SimpleObject simpleObject = new SimpleObject(1, 'b');

		boolean isValid = new Validator<SimpleObject>()
				.map(o -> o.a).check(new Equals<>(1))
				.validate(simpleObject);

		assertTrue(isValid);
	}

}
