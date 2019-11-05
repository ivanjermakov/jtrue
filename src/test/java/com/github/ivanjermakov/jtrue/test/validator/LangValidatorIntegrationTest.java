package com.github.ivanjermakov.jtrue.test.validator;

import com.github.ivanjermakov.jtrue.lang.model.ValidationPredicate;
import com.github.ivanjermakov.jtrue.model.SimpleObject;
import com.github.ivanjermakov.jtrue.util.SourceLoader;
import com.github.ivanjermakov.jtrue.validator.LangValidator;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LangValidatorIntegrationTest {

	@Test
	public void shouldValidateSimpleObjectWithQueryS1() {
		shouldValidateSimpleObjectWithQuery("/lang/s1_basic.true", new SimpleObject(1, 'b'));
	}

	@Test
	public void shouldValidateSimpleObjectWithQueryS2() {
		shouldValidateSimpleObjectWithQuery("/lang/s2_nested.true", new SimpleObject(1, 'b'));
	}

	@Test
	public void shouldValidateSimpleObjectWithQueryS3() {
		shouldValidateSimpleObjectWithQuery("/lang/s3_negate.true", new SimpleObject(1, 'b'));
	}

	@Test
	public void shouldValidateSimpleObjectWithQueryS4() {
		shouldValidateSimpleObjectWithQuery("/lang/s4_or.true", new SimpleObject(1, 'b'));
	}

	@Test
	public void shouldValidateSimpleObjectWithQueryS5() {
		shouldValidateSimpleObjectWithQuery("/lang/s5_params.true", new SimpleObject(1, 'b'));
	}

	@Test
	public void shouldValidateSimpleObjectWithQueryS6() {
		shouldValidateSimpleObjectWithQuery("/lang/s6_root_selector.true", new SimpleObject(1, 'b'));
	}

	@Ignore
	@Test
	public void shouldValidateSimpleObjectWithQueryS7() {
		shouldValidateSimpleObjectWithQuery("/lang/s7_all.true", new SimpleObject(1, 'b'));
	}

	@Ignore
	@Test
	public void shouldValidateSimpleObjectWithQueryS8() {
		shouldValidateSimpleObjectWithQuery("/lang/s8_any.true", new SimpleObject(1, 'b'));
	}

	@Test
	public void shouldValidateSimpleObjectWithQueryS9() {
		shouldValidateSimpleObjectWithQuery("/lang/s9_params.true", new SimpleObject(1, 'b'));
	}

	private void shouldValidateSimpleObjectWithQuery(String path, SimpleObject object) {
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

		LangValidator<SimpleObject> validator = new LangValidator<>(
				SourceLoader.load(path),
				predicateMap
		);

		boolean result = validator.validate(object);

		Assert.assertTrue(result);
	}

}
