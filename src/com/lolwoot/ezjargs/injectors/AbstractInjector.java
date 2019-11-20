package com.lolwoot.ezjargs.injectors;

import java.lang.reflect.Field;

public abstract class AbstractInjector {

	protected Object bean;

	protected Field field;
	
	public AbstractInjector(Object bean, Field field) {
		this.bean = bean;
		this.field = field;
	}

	public abstract void inject(Object value);

}
