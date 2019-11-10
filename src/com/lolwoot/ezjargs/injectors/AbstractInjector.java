package com.lolwoot.ezjargs.injectors;

import java.lang.reflect.Field;

public abstract class AbstractInjector {

	protected Field field;
	
	public AbstractInjector(Field field) {
		this.field = field;
	}

	public abstract void inject(Object value);

}
