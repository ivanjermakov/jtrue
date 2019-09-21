package com.github.ivanjermakov1.jtrue.integration;

import com.github.ivanjermakov1.jtrue.core.Validator;
import com.github.ivanjermakov1.jtrue.exception.InvalidObjectException;
import com.github.ivanjermakov1.jtrue.model.ComplexObject;
import com.github.ivanjermakov1.jtrue.model.CustomException;
import com.github.ivanjermakov1.jtrue.model.EmptyObject;
import com.github.ivanjermakov1.jtrue.model.SimpleObject;
import com.github.ivanjermakov1.jtrue.predicate.Equals;
import com.github.ivanjermakov1.jtrue.predicate.NotNull;
import com.github.ivanjermakov1.jtrue.predicate.Null;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ValidatorIntegrationTest {

	private EmptyObject emptyObject;
	private SimpleObject simpleObject;
	private ComplexObject complexObject;

	@Before
	public void before() {
		emptyObject = new EmptyObject();
		simpleObject = new SimpleObject(1, 'b');
		complexObject = new ComplexObject(1, simpleObject);
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
				.map(SimpleObject::getA).check(new Equals<>(1))
				.validate(simpleObject);

		assertTrue(isValid);
	}

	@Test
	public void shouldValidateSimpleObjectWithCheck() {
		boolean isValid = new Validator<SimpleObject>()
				.check(new NotNull<>())
				.validate(simpleObject);

		assertTrue(isValid);
	}

	@Test
	public void shouldValidateSimpleObjectWithCheckAndMessage() {
		boolean isValid = new Validator<SimpleObject>()
				.check(new NotNull<>())
				.validate(simpleObject);

		assertTrue(isValid);
	}

	@Test
	public void shouldListOneErrorWithCheck() {
		List<String> errors = new Validator<SimpleObject>()
				.check(new Null<>())
				.listErrors(simpleObject);

		assertEquals(1, errors.size());
	}

	@Test
	public void shouldListOneErrorWithMessage() {
		String message = "error message";

		List<String> errors = new Validator<SimpleObject>()
				.check(new Null<>(), message)
				.listErrors(simpleObject);

		assertEquals(1, errors.size());
		assertEquals(message, errors.get(0));
	}

	@Test
	public void shouldNotValidateSimpleObject() {
		boolean isValid = new Validator<SimpleObject>()
				.map(SimpleObject::getA).check(new Equals<>(2))
				.validate(simpleObject);

		assertFalse(isValid);
	}

	@Test(expected = InvalidObjectException.class)
	public void shouldThrowException_WhenNotValidateSimpleObject() throws Throwable {
		new Validator<SimpleObject>()
				.map(SimpleObject::getA).check(new Equals<>(2))
				.throwInvalid(simpleObject);
	}

	@Test
	public void shouldNotThrowException_WhenValidateSimpleObject() throws Throwable {
		new Validator<SimpleObject>()
				.map(SimpleObject::getA).check(new Equals<>(1))
				.throwInvalid(simpleObject);
	}

	@Test(expected = CustomException.class)
	public void shouldThrowCustomException_WhenNotValidateSimpleObject() throws Throwable {
		new Validator<SimpleObject>()
				.map(SimpleObject::getA).check(new Equals<>(2))
				.throwing(() -> new CustomException("custom message"))
				.throwInvalid(simpleObject);
	}

	@Test
	public void shouldListError_WhenNotValidateSimpleObject() throws Throwable {
		CustomException exception = new CustomException("custom message");

		try {
			new Validator<SimpleObject>()
					.map(SimpleObject::getA).check(new Equals<>(2))
					.throwing(() -> exception)
					.throwInvalid(simpleObject);

			fail();
		} catch (CustomException e) {
			assertEquals(exception.getMessage(), e.getMessage());
		}
	}

	@Test
	public void shouldNotThrowCustomException_WhenValidateSimpleObject() throws Throwable {
		new Validator<SimpleObject>()
				.map(SimpleObject::getA).check(new Equals<>(1))
				.throwing(() -> new CustomException("custom message"))
				.throwInvalid(simpleObject);
	}

	@Test
	public void shouldValidateComplexObjectWithoutUse() {
		new Validator<ComplexObject>()
				.map(ComplexObject::getA).check(new Equals<>(1))
				.map(o -> o.getB().getB()).check(new Equals<>('b'))
				.validate(complexObject);
	}

	@Test
	public void shouldValidateComplexObjectWithUse() {
		new Validator<ComplexObject>()
				.map(ComplexObject::getA).check(new Equals<>(1))
				.map(ComplexObject::getB)
				.use(new Validator<SimpleObject>()
						.map(SimpleObject::getB).check(new Equals<>('b')))
				.validate(complexObject);
	}

	@Test
	public void shouldListErrorsEmpty() {
		List<String> errors = new Validator<SimpleObject>()
				.map(SimpleObject::getA).check(new Equals<>(1))
				.listErrors(simpleObject);

		assertTrue(errors.isEmpty());
	}

	@Test
	public void shouldListOneDefaultError() {
		List<String> errors = new Validator<SimpleObject>()
				.map(SimpleObject::getA).check(new Equals<>(2))
				.listErrors(simpleObject);

		assertEquals(1, errors.size());
	}

	@Test
	public void shouldListOneCustomError() {
		List<String> errors = new Validator<SimpleObject>()
				.map(SimpleObject::getA).check(new Equals<>(2), "custom error")
				.listErrors(simpleObject);

		assertEquals(1, errors.size());
	}

}
