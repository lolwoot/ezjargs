package com.lolwoot.ezjargs;

import java.lang.reflect.Field;

import com.lolwoot.ezjargs.ArgumentLine;
import com.lolwoot.ezjargs.processors.Processor;
import com.lolwoot.ezjargs.injectors.AbstractInjector;


public class SingleArgumentInfo extends ArgumentInfo {

	public SingleArgumentInfo(Object bean, Field field, Processor<?> processor) {
		super(bean, field, processor);
	}

	public Object process(ArgumentLine line) {
		Object retValue = processor.process(line);

		//set next ptr
		line.proceed(1);

		return retValue;
	}

	protected AbstractInjector createInjector(Field field) {
		return new SingleInjector(field);
	}

	//TODO replace with consumer?
	private class SingleInjector extends AbstractInjector {
		public SingleInjector(Field field) {
			super(field);
		}
		public void inject(Object value) {
			try {
				this.field.setAccessible(true);
				this.field.set(bean, value);

			} catch(IllegalAccessException e) {
				//TODO exception handling
				e.printStackTrace();
			}
		}
	}
}
