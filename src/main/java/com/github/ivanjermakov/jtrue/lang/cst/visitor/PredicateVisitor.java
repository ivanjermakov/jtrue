package com.github.ivanjermakov.jtrue.lang.cst.visitor;

import com.github.ivanjermakov.antlr.JtrueParser;
import com.github.ivanjermakov.jtrue.exception.LangException;
import com.github.ivanjermakov.jtrue.exception.SyntaxException;
import com.github.ivanjermakov.jtrue.lang.cst.VisitorConfiguration;
import com.github.ivanjermakov.jtrue.lang.model.ValidationError;
import com.github.ivanjermakov.jtrue.lang.model.ValidationPredicate;
import com.github.ivanjermakov.jtrue.lang.model.ValidationResult;
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

		ValidationPredicate<T> validationPredicate = config.predicateMap.get(name);
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

		boolean result = validationPredicate.test(config.target, params);

		ValidationError error = new ValidationError();
		if (!result && errorMessage != null) {
			String message = this.errorMessage.str().getText();
			error = new ValidationError(message);
		}

		return new ValidationResult(result, error);
	}

}
