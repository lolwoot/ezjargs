package com.lolwoot.ezjargs.options;

import java.lang.reflect.Field;

import com.lolwoot.ezjargs.ParametersLine;
import com.lolwoot.ezjargs.processors.Processor;
import com.lolwoot.ezjargs.injectors.AbstractInjector;

public abstract class AbstractOption {

	private AbstractInjector injector;

	protected Processor<?> processor;

	public AbstractOption(Object bean, Field field, Processor<?> processor) {
		this.injector = createInjector(bean, field);
		this.processor = processor;
	}

	public void process(ParametersLine line) {
		Object val = processOpt(line);
		this.injector.inject(val);
	}

	protected abstract Object processOpt(ParametersLine line);

	protected abstract AbstractInjector createInjector(Object bean, Field field);
}

