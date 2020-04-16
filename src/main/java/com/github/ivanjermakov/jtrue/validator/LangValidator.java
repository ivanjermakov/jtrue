package com.github.ivanjermakov.jtrue.validator;

import com.github.ivanjermakov.jtrue.lang.ast.LangParser;
import com.github.ivanjermakov.jtrue.lang.cst.VisitorConfiguration;
import com.github.ivanjermakov.jtrue.lang.cst.visitor.ObjectVisitor;
import com.github.ivanjermakov.jtrue.lang.model.ValidationPredicate;
import com.github.ivanjermakov.jtrue.lang.model.ValidationResult;
import com.github.ivanjermakov.jtrue.lang.model.ValidationTree;
import com.github.ivanjermakov.jtrue.predicate.Equals;
import com.github.ivanjermakov.jtrue.predicate.False;
import com.github.ivanjermakov.jtrue.predicate.GreaterThan;
import com.github.ivanjermakov.jtrue.predicate.Length;
import com.github.ivanjermakov.jtrue.predicate.LessThan;
import com.github.ivanjermakov.jtrue.predicate.NotBlank;
import com.github.ivanjermakov.jtrue.predicate.NotEmptyCollection;
import com.github.ivanjermakov.jtrue.predicate.NotEmptyString;
import com.github.ivanjermakov.jtrue.predicate.NotNull;
import com.github.ivanjermakov.jtrue.predicate.Null;
import com.github.ivanjermakov.jtrue.predicate.Size;
import com.github.ivanjermakov.jtrue.predicate.True;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Map.Entry;

/**
 * Low-level implementation of validator that uses jtrue validation language
 *
 * @param <T> type of object to be validated
 */
public class LangValidator<T> implements Validatable<T> {

	private final String source;
	//	TODO: better system to include predicates into validator
	//	TODO: predefined predicates
	private final Map<String, ValidationPredicate<?>> predicateMap;

	public LangValidator(String source, Map<String, ValidationPredicate<?>> predicateMap) {
		this.source = source;
		this.predicateMap = Stream
				.of(predefinedPredicates(), predicateMap)
				.flatMap(m -> m.entrySet().stream())
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue));
	}

	@Override
	public boolean validate(T t) {
		return check(t).isValid;
	}

	public ValidationTree tree(T t) {
		return check(t).validationTree;
	}

	private ValidationResult check(T t) {
		return new ObjectVisitor<T>(
				new LangParser().parse(source).root,
				new VisitorConfiguration<T>(t, predicateMap)
		).visit();
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	private Map<String, ValidationPredicate<?>> predefinedPredicates() {
		return Map.ofEntries(
				Map.entry("Equals", (o, args) -> new Equals<>(args[0]).test(o)),
				Map.entry("False", (o, args) -> new False<>().test(o)),
				Map.entry("GreaterThan", (o, args) -> new GreaterThan((Comparable) args[0]).test((Comparable) o)),
				Map.entry("Length", (o, args) -> new Length((int) args[0]).test((String) o)),
				Map.entry("LessThan", (o, args) -> new LessThan<>((Comparable) args[0]).test((Comparable) o)),
				Map.entry("NotBlank", (o, args) -> new NotBlank().test((String) o)),
				Map.entry("NotEmptyCollection", (o, args) -> new NotEmptyCollection().test((Collection) o)),
				Map.entry("NotEmptyString", (o, args) -> new NotEmptyString().test((String) o)),
				Map.entry("NotNull", (o, args) -> new NotNull<>().test(o)),
				Map.entry("Null", (o, args) -> new Null<>().test(o)),
				Map.entry("Size", (o, args) -> new Size((int) args[0]).test((Collection) o)),
				Map.entry("True", (o, args) -> new True<>().test(o))
		);
	}

}
