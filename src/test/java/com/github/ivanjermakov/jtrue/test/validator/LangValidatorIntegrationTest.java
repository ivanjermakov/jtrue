package com.github.ivanjermakov.jtrue.test.validator;

import com.github.ivanjermakov.jtrue.lang.model.ValidationPredicate;
import com.github.ivanjermakov.jtrue.model.SimpleObject;
import com.github.ivanjermakov.jtrue.util.SourceLoader;
import com.github.ivanjermakov.jtrue.validator.LangValidator;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class LangValidatorIntegrationTest {

	@Test
	public void shouldValidateSimpleObjectWithQueryS1() {

		Map<String, ValidationPredicate> predicateMap = new HashMap<>();
		predicateMap.put("NotNull", (s, o) -> s != null);
		predicateMap.put("Null", (s, o) -> s == null);

		LangValidator<SimpleObject> validator = new LangValidator<>(
				SourceLoader.load("/lang/s1.true"),
				predicateMap
		);

		boolean result = validator.validate(new SimpleObject(1, 'b'));

		Assert.assertTrue(result);
	}
}
