package com.lolwoot.ezjargs;

import java.lang.reflect.Field;

import com.lolwoot.ezjargs.ArgumentLine;
import com.lolwoot.ezjargs.processors.Processor;
import com.lolwoot.ezjargs.injectors.AbstractInjector;

public class MultiArgumentInfo extends ArgumentInfo {

	public MultiArgumentInfo(Object bean, Field field, Processor<?> processor) {
		super(bean, field, processor);
	}

	public Object process(ArgumentLine line) {
		//TODO process multi args || REFACTOR
		System.out.println("MultiArgumentInfo--->process(Not implemented)");
		return null;
	}
				

	//TODO implement
	protected AbstractInjector createInjector(Field field) {
		System.out.println("MultiArgumentInfo--->CreateInjector(Not implemented)");
		return null;
	}

}
