package com.github.ivanjermakov.jtrue.lang.cst.visitor;

import com.github.ivanjermakov.antlr.JtrueParser;
import com.github.ivanjermakov.jtrue.exception.SyntaxException;
import com.github.ivanjermakov.jtrue.lang.cst.VisitorConfiguration;
import com.github.ivanjermakov.jtrue.lang.model.FieldValue;
import com.github.ivanjermakov.jtrue.lang.model.ValidationResult;

public class FieldSelectorVisitor<T> implements LangVisitor<ValidationResult> {

	private final JtrueParser.FieldSelectorContext fieldSelector;
	private final JtrueParser.ObjectContext object;
	private final VisitorConfiguration<T> config;

	public FieldSelectorVisitor(JtrueParser.FieldSelectorContext fieldSelector, JtrueParser.ObjectContext object, VisitorConfiguration<T> config) {
		this.fieldSelector = fieldSelector;
		this.object = object;
		this.config = config;
	}

	@Override
	public ValidationResult visit() throws SyntaxException {
		if (fieldSelector.singleFieldSelector() != null) {
			FieldValue fieldValue = new SingleFieldSelectorVisitor<>(fieldSelector.singleFieldSelector(), config).visit();
			return new ObjectVisitor<>(object, config.withTarget(fieldValue.value)).visit();
		}

//		TODO: all any support
		/*if (fieldSelector.allFieldSelector() == null && fieldSelector.anyFieldSelector() == null) {
			throw new SyntaxException("'allFieldSelector' node must contain 'fieldPath' node");
		}

		List<FieldValue> fieldValues;
		if (fieldSelector.allFieldSelector() != null) {
			fieldValues = new AllFieldSelectorVisitor<>(fieldSelector.allFieldSelector(), config).visit();
			JtrueParser.AndGroupContext andGroup = new JtrueParser.AndGroupContext(null, 1);
			andGroup.addChild()

			new AndGroupVisitor<>(andGroup, config.withTarget(fieldValue));
		}
		if (fieldSelector.anyFieldSelector() != null) {
			fieldValues = new AnyFieldSelectorVisitor<>(fieldSelector.anyFieldSelector(), config).visit();
		}*/
		throw new SyntaxException();
	}

}
