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
		List<String> fieldNames = Arrays.asList(fieldPath.getText().split("\\."));
		return new FieldPath(fieldNames.subList(fieldNames.isEmpty() ? 0 : 1, fieldNames.size()));
	}

}
