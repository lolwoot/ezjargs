package com.lolwoot.ezjargs.processors;

import com.lolwoot.ezjargs.ParametersLine;

public interface Processor<T> {
	public T process(ParametersLine line);
}
