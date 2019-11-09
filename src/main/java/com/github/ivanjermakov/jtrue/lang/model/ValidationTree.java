package com.github.ivanjermakov.jtrue.lang.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ValidationTree {

	public final String errorMessage;
	public final List<ValidationTree> children;
	public final boolean isValid;

	public ValidationTree(boolean isValid, String errorMessage) {
		this(isValid, errorMessage, Collections.emptyList());
	}

	public ValidationTree(boolean isValid, List<ValidationTree> children) {
		this(isValid, "", children);
	}

	public ValidationTree(boolean isValid) {
		this(isValid, "", Collections.emptyList());
	}

	public ValidationTree(boolean isValid, String errorMessage, List<ValidationTree> children) {
		this.isValid = isValid;
		this.errorMessage = errorMessage;
		this.children = children;
	}

	public List<ValidationTree> flat() {
		return flat(this);
	}

	@Override
	public String toString() {
		String result = (isValid ? "Valid" : "Invalid").concat(" node");

		if (!errorMessage.isEmpty()) {
			result = result
					.concat(": ")
					.concat(errorMessage);
		}

		if (children.isEmpty()) return result;

		return result
				.concat("\n")
				.concat("children:")
				.concat("\n")
				.concat(children
						.stream()
						.map(ValidationTree::toString)
						.map("    "::concat)
						.map(s -> s.replace("\n", "\n    "))
						.collect(Collectors.joining(",\n"))
				);
	}

	private List<ValidationTree> flat(ValidationTree tree) {
		if (tree.children.isEmpty()) return Collections.singletonList(tree);

		return tree.children
				.stream()
				.flatMap(c -> flat(c).stream())
				.collect(Collectors.toList());
	}

}
