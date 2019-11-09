package com.github.ivanjermakov.jtrue.test.validator;

import com.github.ivanjermakov.jtrue.exception.FieldAccessException;
import com.github.ivanjermakov.jtrue.lang.model.ValidationPredicate;
import com.github.ivanjermakov.jtrue.lang.model.ValidationTree;
import com.github.ivanjermakov.jtrue.model.ComplexObject;
import com.github.ivanjermakov.jtrue.model.SimpleObject;
import com.github.ivanjermakov.jtrue.util.SourceLoader;
import com.github.ivanjermakov.jtrue.validator.LangValidator;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LangValidatorIntegrationTest {

	@Test
	public void shouldValidateSimpleObjectWithQueryS1() {
		LangValidator<SimpleObject> validator = setup("/lang/s1_basic.true");
		boolean result = validator.validate(new SimpleObject(1, 'b'));

		assertTrue(result);
	}

	@Test
	public void shouldValidateSimpleObjectWithQueryS2() {
		LangValidator<SimpleObject> validator = setup("/lang/s2_nested.true");
		boolean result = validator.validate(new SimpleObject(1, 'b'));

		assertTrue(result);
	}

	@Test
	public void shouldValidateSimpleObjectWithQueryS3() {
		LangValidator<SimpleObject> validator = setup("/lang/s3_negate.true");
		boolean result = validator.validate(new SimpleObject(1, 'b'));

		assertTrue(result);
	}

	@Test
	public void shouldValidateSimpleObjectWithQueryS4() {
		LangValidator<SimpleObject> validator = setup("/lang/s4_or.true");
		boolean result = validator.validate(new SimpleObject(1, 'b'));

		assertTrue(result);
	}

	@Test
	public void shouldValidateSimpleObjectWithQueryS5() {
		LangValidator<SimpleObject> validator = setup("/lang/s5_params.true");
		boolean result = validator.validate(new SimpleObject(1, 'b'));

		assertTrue(result);
	}

	@Test
	public void shouldValidateSimpleObjectWithQueryS6() {
		LangValidator<SimpleObject> validator = setup("/lang/s6_root_selector.true");
		boolean result = validator.validate(new SimpleObject(1, 'b'));

		assertTrue(result);
	}

	@Test
	public void shouldValidateSimpleObjectWithQueryS7() {
	}

	@Test
	public void shouldValidateSimpleObjectWithQueryS8() {
		LangValidator<SimpleObject> validator = setup("/lang/s8_any.true");
		boolean result = validator.validate(new SimpleObject(1, 'b'));

		assertTrue(result);
	}

	@Test
	public void shouldValidateSimpleObjectWithQueryS9() {
		LangValidator<SimpleObject> validator = setup("/lang/s9_params.true");
		boolean result = validator.validate(new SimpleObject(1, 'b'));

		assertTrue(result);
	}

	@Test
	public void shouldNotValidateSimpleObjectWithQueryS10() {
		LangValidator<SimpleObject> validator = setup("/lang/s10_all.true");
		boolean result = validator.validate(new SimpleObject(1, 'b'));

		assertFalse(result);
	}

	@Test
	public void shouldNotValidateSimpleObjectWithQueryS11() {
		LangValidator<SimpleObject> validator = setup("/lang/s11_any.true");
		boolean result = validator.validate(new SimpleObject(1, 'b'));

		assertTrue(result);
	}

	@Test
	public void shouldValidateComplexObjectWithQueryC1() {
		LangValidator<ComplexObject> validator = setup("/lang/c1.true");
		boolean result = validator.validate(new ComplexObject(null, new SimpleObject(1, 'b')));

		assertTrue(result);
	}

	@Test(expected = FieldAccessException.class)
	public void shouldNotValidateComplexObjectWithQueryC2() {
		LangValidator<ComplexObject> validator = setup("/lang/c2.true");
		validator.validate(new ComplexObject(null, new SimpleObject(1, 'b')));
	}

	@Test
	public void shouldValidateSimpleObjectWithoutErrorE1() {
		LangValidator<SimpleObject> validator = setup("/lang/e1.true");
		ValidationTree tree = validator.tree(new SimpleObject(1, 'b'));

		assertTrue(tree.isValid);
	}

	@Test
	public void shouldNotValidateSimpleObjectWithErrorE1() {
		LangValidator<SimpleObject> validator = setup("/lang/e1.true");
		ValidationTree tree = validator.tree(null);

		assertFalse(tree.isValid);
		assertTrue(tree.errorMessage.isEmpty());
		assertEquals("the object is null", tree.children.get(0).errorMessage);
	}

	@Test
	public void shouldValidateSimpleObjectWithoutErrorE3() {
		LangValidator<SimpleObject> validator = setup("/lang/e3.true");
		ValidationTree tree = validator.tree(new SimpleObject(1, 'b'));

		assertTrue(tree.isValid);
		assertEquals("the object is null", tree.flat().get(0).errorMessage);
	}

	@Test
	public void shouldNotValidateComplexObjectWithErrorE2() {
		LangValidator<ComplexObject> validator = setup("/lang/e2.true");
		ValidationTree tree = validator.tree(new ComplexObject(null, new SimpleObject(1, 'b')));

		assertFalse(tree.isValid);
		List<ValidationTree> flat = tree.flat();
		System.out.println(tree);
		assertEquals(2, flat.stream().filter(t -> !t.isValid).count());
	}

	@Test
	public void shouldNotValidatePrimitiveWithErrorE4() {
		LangValidator<Integer> validator = setup("/lang/e4.true");
		ValidationTree tree = validator.tree(1);

		assertFalse(tree.isValid);
		List<ValidationTree> flat = tree.flat();
		System.out.println(tree);
		assertEquals(
				"not equal to 5",
				flat
						.stream()
						.filter(t -> !t.isValid)
						.findFirst()
						.orElseThrow()
						.errorMessage
		);
	}

	private <T> LangValidator<T> setup(String path) {
		Map<String, ValidationPredicate> predicateMap = new HashMap<>();
		predicateMap.put("NotNull", (o, __) -> o != null);
		predicateMap.put("Null", (o, __) -> o == null);
		predicateMap.put("True", (o, __) -> true);
		predicateMap.put("Equals", (o, args) -> Objects.equals(o, args[0]));
		predicateMap.put("Custom", (o, args) ->
				args[0] instanceof Integer && args[0].equals(1) &&
						args[1] instanceof Double && args[1].equals(-12.453) &&
						args[2] instanceof String && args[2].equals("Str   some") &&
						args[3] instanceof Boolean && args[3].equals(true) &&
						args[4] instanceof Boolean && args[4].equals(false));

		return new LangValidator<>(
				SourceLoader.load(path),
				predicateMap
		);
	}

}
