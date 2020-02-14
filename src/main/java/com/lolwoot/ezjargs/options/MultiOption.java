package com.lolwoot.ezjargs.options;

import com.lolwoot.ezjargs.ParametersLine;
import com.lolwoot.ezjargs.injectors.AbstractInjector;
import com.lolwoot.ezjargs.injectors.ArrayInjector;
import com.lolwoot.ezjargs.processors.Processor;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class MultiOption extends AbstractOption {

	public MultiOption(Object bean, Field field, Processor<?> processor, OptionDescription optD) {
		super(bean, field, processor, optD);
	}

	//TODO replace ArrayList?
	public Object processOpt(ParametersLine line) {

		ArrayList<Object> list = new ArrayList<>();
		while (line.hasNext() && !line.isNextOption()) {
			list.add(processor.process(line));
		}
		System.out.println(list);
		return list.toArray();
	}
				

	//TODO use factory to create different injector (array, list, etc)
	protected AbstractInjector createInjector(Object bean, Field field) {
		AbstractInjector injector = null;
		if(field.getType().isArray()) {
			injector = new ArrayInjector(bean, field);
		}
		return injector;	
	}
}
