package com.github.ivanjermakov.jtrue.lang.cst.visitor;

import com.github.ivanjermakov.antlr.JtrueParser;
import com.github.ivanjermakov.jtrue.lang.model.FieldPath;

import java.util.Arrays;
import java.util.List;

public class FieldPathVisitor implements LangVisitor<FieldPath> {

	private final JtrueParser.FieldPathContext fieldPath;

	public FieldPathVisitor(JtrueParser.FieldPathContext fieldPath) {
		this.fieldPath = fieldPath;
	}

	@Override
	public FieldPath visit() {
		List<String> fieldNames = Arrays.asList(fieldPath.getText().substring(1).split("\\."));
		return new FieldPath(fieldNames.subList(1, fieldNames.size()));
	}

}
