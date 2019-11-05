package com.github.ivanjermakov.jtrue.lang.cst.visitor;

import com.github.ivanjermakov.antlr.JtrueParser;
import com.github.ivanjermakov.jtrue.exception.SyntaxException;
import com.github.ivanjermakov.jtrue.lang.cst.VisitorConfiguration;
import com.github.ivanjermakov.jtrue.lang.model.ValidationResult;

public class GroupVisitor<T> implements LangVisitor<ValidationResult> {

	private final JtrueParser.GroupContext group;
	private final VisitorConfiguration<T> config;

	public GroupVisitor(JtrueParser.GroupContext group, VisitorConfiguration<T> config) {
		this.group = group;
		this.config = config;
	}

	@Override
	public ValidationResult visit() {
		if (group.andGroup() != null) {
			return new AndGroupVisitor<>(group.andGroup(), config).visit();
		}
		if (group.orGroup() != null) {
			return new OrGroupVisitor<>(group.orGroup(), config).visit();
		}

		throw new SyntaxException("'group' node must contain 'and_group' or 'or_group' nodes");
	}

}
