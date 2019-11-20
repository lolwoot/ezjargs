package com.lolwoot.ezjargs.options;

import java.lang.reflect.Field;

import com.lolwoot.ezjargs.ParametersLine;
import com.lolwoot.ezjargs.processors.Processor;
import com.lolwoot.ezjargs.injectors.AbstractInjector;
import com.lolwoot.ezjargs.injectors.SingleInjector;


public class SingleOption extends AbstractOption {

	public SingleOption(Object bean, Field field, Processor<?> processor) {
		super(bean, field, processor);
	}

	public Object processOpt(ParametersLine line) {
		return processor.process(line);
	}

	protected AbstractInjector createInjector(Object bean, Field field) {
		return new SingleInjector(bean, field);
	}
}
