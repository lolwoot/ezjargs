package com.lolwoot.ezjargs.injectors;

import com.lolwoot.ezjargs.injectors.AbstractInjector;

import java.lang.reflect.Field;

public class SingleInjector extends AbstractInjector {

	public SingleInjector(Object bean, Field field) {
		super(bean, field);
	}
	
	public void inject(Object value) {
		try {
			this.field.setAccessible(true);
			this.field.set(bean, value);
			System.out.println("Injected");
		} catch(IllegalAccessException e) {
			//TODO exception handling
			e.printStackTrace();
		}
	}
}
