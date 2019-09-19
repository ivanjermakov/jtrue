package com.gmail.ivanjermakov1.jtrue.integration;

import com.gmail.ivanjermakov1.jtrue.core.Validator;
import com.gmail.ivanjermakov1.jtrue.exception.InvalidObjectException;
import com.gmail.ivanjermakov1.jtrue.model.CustomException;
import com.gmail.ivanjermakov1.jtrue.model.EmptyObject;
import com.gmail.ivanjermakov1.jtrue.model.SimpleObject;
import com.gmail.ivanjermakov1.jtrue.predicate.Equals;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidatorIntegrationTest {

	private EmptyObject emptyObject;
	private SimpleObject simpleObject;

	@Before
	public void before() {
		emptyObject = new EmptyObject();
		simpleObject = new SimpleObject(1, 'b');
	}

	@Test
	public void shouldValidateEmptyObject() {

		boolean isValid = new Validator<EmptyObject>()
				.validate(emptyObject);

		assertTrue(isValid);
	}

	@Test
	public void shouldValidateSimpleObjectWithoutChecks() {
		boolean isValid = new Validator<SimpleObject>()
				.validate(simpleObject);

		assertTrue(isValid);
	}

	@Test
	public void shouldValidateSimpleObjectMap() {
		boolean isValid = new Validator<SimpleObject>()
				.map(o -> o.a).check(new Equals<>(1))
				.validate(simpleObject);

		assertTrue(isValid);
	}

	@Test
	public void shouldNotValidateSimpleObject() {
		boolean isValid = new Validator<SimpleObject>()
				.map(o -> o.a).check(new Equals<>(2))
				.validate(simpleObject);

		assertFalse(isValid);
	}

	@Test(expected = InvalidObjectException.class)
	public void shouldThrowException_WhenNotValidateSimpleObject() throws Throwable {
		new Validator<SimpleObject>()
				.map(o -> o.a).check(new Equals<>(2))
				.throwInvalid(simpleObject);
	}

	@Test
	public void shouldNotThrowException_WhenValidateSimpleObject() throws Throwable {
		new Validator<SimpleObject>()
				.map(o -> o.a).check(new Equals<>(1))
				.throwInvalid(simpleObject);
	}

	@Test(expected = CustomException.class)
	public void shouldThrowCustomException_WhenNotValidateSimpleObject() throws Throwable {
		new Validator<SimpleObject>()
				.map(o -> o.a).check(new Equals<>(2))
				.throwInvalid(simpleObject, () -> new CustomException("custom message"));
	}

	@Test
	public void shouldNotThrowCustomException_WhenValidateSimpleObject() throws Throwable {
		new Validator<SimpleObject>()
				.map(o -> o.a).check(new Equals<>(1))
				.throwInvalid(simpleObject, () -> new CustomException("custom message"));
	}

}
