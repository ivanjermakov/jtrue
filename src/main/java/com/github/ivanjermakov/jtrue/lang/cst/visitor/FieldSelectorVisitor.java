package com.github.ivanjermakov.jtrue.lang.cst.visitor;

import com.github.ivanjermakov.antlr.JtrueParser;
import com.github.ivanjermakov.jtrue.exception.SyntaxException;
import com.github.ivanjermakov.jtrue.lang.cst.VisitorConfiguration;
import com.github.ivanjermakov.jtrue.lang.model.FieldValue;
import com.github.ivanjermakov.jtrue.lang.model.ValidationResult;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;
import java.util.stream.Collectors;

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

		if (fieldSelector.allFieldSelector() == null && fieldSelector.anyFieldSelector() == null) {
			throw new SyntaxException("'allFieldSelector' node must contain 'fieldPath' node");
		}

		JtrueParser.ValidationRuleContext validationRule = (JtrueParser.ValidationRuleContext) fieldSelector.parent.parent;
		validationRule.children.clear();

		JtrueParser.GroupContext group = null;
		if (fieldSelector.allFieldSelector() != null) {
			group = transformForFieldSelector((JtrueParser.FieldRuleContext) fieldSelector.parent, true);
		}

		if (fieldSelector.anyFieldSelector() != null) {
			group = transformForFieldSelector((JtrueParser.FieldRuleContext) fieldSelector.parent, false);
		}

		group.setParent(validationRule);
		validationRule.addChild(group);

		return new GroupVisitor<>(group, config.withTarget(config.target)).visit();
	}

	private JtrueParser.GroupContext transformForFieldSelector(JtrueParser.FieldRuleContext fieldRule, boolean isAnd) {
		JtrueParser.FieldSelectorContext fieldSelector = fieldRule.fieldSelector();
		JtrueParser.FieldPathsContext fieldPaths;
		if (isAnd) {
			fieldPaths = fieldSelector.allFieldSelector().fieldPaths();
		} else {
			fieldPaths = fieldSelector.anyFieldSelector().fieldPaths();
		}
		JtrueParser.ObjectContext object = fieldRule.object();

		JtrueParser.GroupContext group = new JtrueParser.GroupContext(null, -1);

		ParserRuleContext andOrGroup;
		if (isAnd) {
			andOrGroup = new JtrueParser.AndGroupContext(group, group.invokingState);
		} else {
			andOrGroup = new JtrueParser.OrGroupContext(group, group.invokingState);
		}

		JtrueParser.ValidationRulesContext validationRules = new JtrueParser.ValidationRulesContext(andOrGroup, andOrGroup.invokingState);

		List<JtrueParser.ValidationRuleContext> validationRuleList = fieldPaths.fieldPath()
				.stream()
				.map(fp -> {
					JtrueParser.ValidationRuleContext vr = new JtrueParser.ValidationRuleContext(validationRules, validationRules.invokingState);
					JtrueParser.FieldRuleContext fr = new JtrueParser.FieldRuleContext(vr, vr.invokingState);

					JtrueParser.FieldSelectorContext fs = new JtrueParser.FieldSelectorContext(fr, fr.invokingState);
					JtrueParser.SingleFieldSelectorContext sfs = new JtrueParser.SingleFieldSelectorContext(fs, fs.invokingState);
					sfs.addChild(fp);
					fs.addChild(sfs);
					fr.addChild(fs);
					fr.addChild(object);
					vr.addChild(fr);

					return vr;
				})
				.collect(Collectors.toList());

		validationRuleList.forEach(validationRules::addChild);
		andOrGroup.addChild(validationRules);
		group.addChild(andOrGroup);

		return group;
	}

}
