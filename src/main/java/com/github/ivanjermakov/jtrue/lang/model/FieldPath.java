package com.github.ivanjermakov.jtrue.lang.model;

import com.github.ivanjermakov.jtrue.exception.FieldAccessException;

import java.lang.reflect.Field;
import java.util.List;

public class FieldPath {

	public final List<String> fieldNames;

	public FieldPath(List<String> fieldNames) {
		this.fieldNames = fieldNames;
	}

	public FieldValue query(Object o) {
		try {
			Object queryField = o;

			for (String fieldName : fieldNames) {
				if (queryField == null) {
					throw new FieldAccessException(
							String.format("accessing field '%s' on object that is null in query '%s'", fieldName, this)
					);
				}
				Field field = queryField.getClass().getDeclaredField(fieldName);
				field.setAccessible(true);
				queryField = field.get(queryField);
			}

			return new FieldValue(queryField);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			throw new FieldAccessException("unable to query field with path '" + this + "'", e);
		}
	}

	@Override
	public String toString() {
		return "." + String.join(".", fieldNames);
	}

}
