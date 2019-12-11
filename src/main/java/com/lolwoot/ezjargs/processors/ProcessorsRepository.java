package com.lolwoot.ezjargs.processors;

import com.lolwoot.ezjargs.exceptions.ProcessorNotFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.io.File;

public class ProcessorsRepository {
	
	private static Map<Class<?>, Processor<?>> map = new HashMap<>();

	//Default parameters handlers
	static {
		map.put(Integer.class, new IntegerProcessor());
		map.put(String.class, new StringProcessor());
		map.put(File.class, new FileProcessor());
	}

	
	public static Processor<?> of(Class<?> clazz) {
		if(clazz.isArray()) {
			clazz = clazz.getComponentType();
		}

		if(!map.containsKey(clazz)) throw new ProcessorNotFoundException(clazz);
		
		return map.get(clazz);
  	}
}
