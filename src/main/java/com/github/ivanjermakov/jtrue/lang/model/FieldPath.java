package com.github.ivanjermakov.jtrue.lang.model;

import java.lang.reflect.Field;
import java.util.List;

public class FieldPath {

	public final List<String> fieldNames;

	public FieldPath(List<String> fieldNames) {
		this.fieldNames = fieldNames;
	}

	public FieldValue query(Object o) throws NoSuchFieldException, IllegalAccessException {
		Object queryField = o;

		for (String fieldName : fieldNames) {
			Field field = queryField.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			queryField = field.get(queryField);
		}

		return new FieldValue(queryField);
	}

	@Override
	public String toString() {
		return String.join(", ", fieldNames);
	}

}
