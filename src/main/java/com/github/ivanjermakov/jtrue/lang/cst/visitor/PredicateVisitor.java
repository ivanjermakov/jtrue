package com.github.ivanjermakov.jtrue.lang.cst.visitor;

import com.github.ivanjermakov.antlr.JtrueParser;
import com.github.ivanjermakov.jtrue.exception.LangException;
import com.github.ivanjermakov.jtrue.exception.SyntaxException;
import com.github.ivanjermakov.jtrue.lang.cst.VisitorConfiguration;
import com.github.ivanjermakov.jtrue.lang.model.ValidationPredicate;
import com.github.ivanjermakov.jtrue.lang.model.ValidationResult;
import com.github.ivanjermakov.jtrue.lang.model.ValidationTree;
import com.github.ivanjermakov.jtrue.lang.type.LangType;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PredicateVisitor<T> implements LangVisitor<ValidationResult> {

	private final JtrueParser.PredicateContext predicate;
	private final JtrueParser.ErrorMessageContext errorMessage;
	private final VisitorConfiguration<T> config;

	public PredicateVisitor(JtrueParser.PredicateContext predicate, @Nullable JtrueParser.ErrorMessageContext errorMessage, VisitorConfiguration<T> config) {
		this.predicate = predicate;
		this.errorMessage = errorMessage;
		this.config = config;
	}

	@Override
	@SuppressWarnings("unchecked")
	public ValidationResult visit() throws SyntaxException {
		String name;
		if (predicate.predicateName() != null) {
			name = predicate.predicateName().getText();
		} else {
			throw new SyntaxException("'predicate' node must contain 'predicateName' node");
		}

		ValidationPredicate<T> validationPredicate = (ValidationPredicate<T>) config.predicateMap.get(name);
		if (validationPredicate == null) {
			throw new LangException("predicate with name '" + name + "' was not found in provided predicate set");
		}

		Object[] params = new Object[]{};
		if (predicate.predicateParameters() != null) {
			List<LangType> parameters = new PredicateParametersVisitor(predicate.predicateParameters()).visit();

			params = parameters
					.stream()
					.map(LangType::value)
					.toArray();
		}

		try {
			boolean result = validationPredicate.test(config.target, params);

			ValidationTree tree = errorMessage == null
					? new ValidationTree(result)
					: new ValidationTree(result, new StrVisitor(this.errorMessage.str()).visit());

			return new ValidationResult(result, tree);
		} catch (NullPointerException e) {
			return new ValidationResult(
					false,
					new ValidationTree(false, "null value passed into not-null predicate")
			);
		}
	}

}
