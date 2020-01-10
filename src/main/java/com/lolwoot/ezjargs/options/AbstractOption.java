package com.lolwoot.ezjargs.options;

import com.lolwoot.ezjargs.ParametersLine;
import com.lolwoot.ezjargs.injectors.AbstractInjector;
import com.lolwoot.ezjargs.processors.Processor;

import java.lang.reflect.Field;

public abstract class AbstractOption {

	private String name;

	private AbstractInjector injector;

	protected Processor<?> processor;

	public AbstractOption(Object bean, Field field, String optName, Processor<?> processor) {
		this.injector = createInjector(bean, field);
		this.processor = processor;
		this.name = optName;
	}

	public void process(ParametersLine line) {
		Object val = processOpt(line);
		this.injector.inject(val);
	}

	/**
	 * Process option value(or values) and increase pointer of current element in ParametersLine
	 * @param line
	 */
	protected abstract Object processOpt(ParametersLine line);

	/**
	 * Factory method for creating injector (single field injector or collection)
	 * @param bean
	 * @param field
	 */
	protected abstract AbstractInjector createInjector(Object bean, Field field);

	public String getName() {
		return name;
	}

	//TODO
	public String getUsage() {
		return "MockUsage. User this option for ...";
	}
}

