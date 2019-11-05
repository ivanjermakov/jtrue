package com.github.ivanjermakov.jtrue.lang.cst.visitor;

import com.github.ivanjermakov.jtrue.exception.SyntaxException;

public interface LangVisitor<R> {

	R visit() throws SyntaxException;

}
