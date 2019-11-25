package com.lolwoot.ezjargs.injectors;

import java.lang.reflect.Field;
import java.lang.reflect.Array;

public class ArrayInjector extends AbstractInjector {

	public ArrayInjector(Object bean, Field field) {
		super(bean, field);
	}

	public void inject(Object value) {
		try {
			int len = Array.getLength(value);
			field.setAccessible(true);
			Object array = Array.newInstance(field.getType().getComponentType(), len);
			System.arraycopy(value, 0, array, 0, len);
			field.set(bean, array);
		} catch(IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}

