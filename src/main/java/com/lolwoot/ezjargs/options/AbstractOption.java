package com.lolwoot.ezjargs.options;

import com.lolwoot.ezjargs.ParametersLine;
import com.lolwoot.ezjargs.injectors.AbstractInjector;
import com.lolwoot.ezjargs.processors.Processor;

import java.lang.reflect.Field;

public abstract class AbstractOption {

	protected final Processor<?> processor;
	private final OptionDescription optInfo;
	private final AbstractInjector injector;

	public AbstractOption(Object bean, Field field, Processor<?> processor, OptionDescription optInfo) {
		this.injector = createInjector(bean, field);
		this.processor = processor;
		this.optInfo = optInfo;

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
	 *
	 * @param bean
	 * @param field
	 */
	protected abstract AbstractInjector createInjector(Object bean, Field field);

	/**
	 * Getting info about option like name, description.
	 *
	 * @return
	 */
	public OptionDescription getOptInfo() {
		return optInfo;
	}
}

