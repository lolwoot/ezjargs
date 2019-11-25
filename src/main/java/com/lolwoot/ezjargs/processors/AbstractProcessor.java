package com.lolwoot.ezjargs.processors;

import com.lolwoot.ezjargs.ParametersLine;

public abstract class AbstractProcessor<T> implements Processor<T> {

	public T process(ParametersLine line) {
		T var = parse(line.next());
		return var;
	}

	protected abstract T parse(String str);
}
 
