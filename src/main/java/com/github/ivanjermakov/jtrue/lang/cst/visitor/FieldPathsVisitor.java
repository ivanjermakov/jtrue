package com.github.ivanjermakov.jtrue.lang.cst.visitor;

import com.github.ivanjermakov.antlr.JtrueParser;
import com.github.ivanjermakov.jtrue.lang.model.FieldPath;

import java.util.List;
import java.util.stream.Collectors;

public class FieldPathsVisitor implements LangVisitor<List<FieldPath>> {

	private final JtrueParser.FieldPathsContext fieldPaths;

	public FieldPathsVisitor(JtrueParser.FieldPathsContext fieldPaths) {
		this.fieldPaths = fieldPaths;
	}

	@Override
	public List<FieldPath> visit() {
		return this.fieldPaths.children
				.stream()
				.filter(c -> c instanceof JtrueParser.FieldPathContext)
				.map(c -> (JtrueParser.FieldPathContext) c)
				.map(fp -> new FieldPathVisitor(fp).visit())
				.collect(Collectors.toList());
	}

}
