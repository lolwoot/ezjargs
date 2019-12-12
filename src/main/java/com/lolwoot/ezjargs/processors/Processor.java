package com.lolwoot.ezjargs.processors;

import com.lolwoot.ezjargs.ParametersLine;

public interface Processor<T> {
	T process(ParametersLine line);
}
