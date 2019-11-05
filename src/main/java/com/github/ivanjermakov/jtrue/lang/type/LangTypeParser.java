package com.github.ivanjermakov.jtrue.lang.type;

public interface LangTypeParser<T extends LangType> {

	T parse(String text);

}
