package com.lolwoot.ezjargs;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.io.File;
import java.lang.reflect.Array;

import com.lolwoot.ezjargs.ArgumentLine;
import com.lolwoot.ezjargs.processors.Processor;
import com.lolwoot.ezjargs.injectors.AbstractInjector;

public class MultiArgumentInfo extends ArgumentInfo {

	public MultiArgumentInfo(Object bean, Field field, Processor<?> processor) {
		super(bean, field, processor);
	}

	public Object process(ArgumentLine line) {
		ArrayList<Object> list = new ArrayList<>();
		while(line.hasNext() && !line.isNextParameter()) {
			list.add(processor.process(line));
		}
		System.out.println(list);
		return list.toArray();
	}
				

	//TODO we can create Array, List or collection injector?
	protected AbstractInjector createInjector(Field field) {
		AbstractInjector injector = null;
		if(field.getType().isArray()) {
			injector = new ArrayInjector(field);
		}
		System.out.println("CreatedInjecter: " + injector.getClass().getName());
		return injector;	
	}

	//TODO refactor
	private class ArrayInjector extends AbstractInjector {
		public ArrayInjector(Field field){
			super(field);
		}
		public void inject(Object value) {
			System.out.println("ArrayInjector--->Injecting");
			
			try{
				field.setAccessible(true);
			
				Object array = Array.newInstance(field.getType().getComponentType(), 10);
				System.arraycopy(value, 0, array, 0, 2);

				field.set(bean, array);
			} catch(IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

}
