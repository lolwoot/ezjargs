package com.lolwoot.ezjargs;

import java.lang.reflect.Field;

import com.lolwoot.ezjargs.processors.Processor;
import com.lolwoot.ezjargs.injectors.AbstractInjector;

public abstract class ArgumentInfo {

	protected Object bean;

	private AbstractInjector injector;

	protected Processor<?> processor;

	public ArgumentInfo(Object bean, Field field, Processor<?> processor) {
		System.out.printf("Create ArgumentInfo:{%s, %s}.\n", field, processor);
		this.bean = bean;
		this.injector = createInjector(field);
		this.processor = processor;
	}

	public void processArg(ArgumentLine line) {
		Object val = process(line);
		//TODO try to inject
		this.injector.inject(val);
	}

	//TODO
	protected abstract Object process(ArgumentLine line);

	//TODO
	protected abstract AbstractInjector createInjector(Field field);
}

