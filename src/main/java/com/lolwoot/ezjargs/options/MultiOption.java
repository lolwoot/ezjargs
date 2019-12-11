package com.lolwoot.ezjargs.options;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.io.File;
import java.lang.reflect.Array;

import com.lolwoot.ezjargs.ParametersLine;
import com.lolwoot.ezjargs.processors.Processor;
import com.lolwoot.ezjargs.injectors.AbstractInjector;
import com.lolwoot.ezjargs.injectors.ArrayInjector;

public class MultiOption extends AbstractOption {

	public MultiOption(Object bean, Field field, String name, Processor<?> processor) {
		super(bean, field, name, processor);
	}

	//TODO replace ArrayList?
	public Object processOpt(ParametersLine line) {

		ArrayList<Object> list = new ArrayList<>();
		while(line.hasNext() && !line.isNextOption()) {
			System.out.println("process add next option value");
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
