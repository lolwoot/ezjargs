package com.lolwoot.ezjargs;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.HashMap;

import com.lolwoot.ezjargs.processors.Processor;
import com.lolwoot.ezjargs.processors.ProcessorsRepository;

public class BeanParser {

	private Object bean;
	
	private Map<String, ArgumentInfo> arguments = new HashMap<>();
	
	public BeanParser(Object bean, ArgumentLine line) {
    
		this.bean = bean;  
		Class<?> clazz = bean.getClass();

    		Field[] fields = clazz.getDeclaredFields();
	    	for(Field field : fields) {
      			System.out.printf("Getting processor for %s.\n", field.getName());

			//TODO check multivalue field
			boolean multiValue = field.getType().isArray();
			System.out.printf("Multivalue: %s. Field class: %s.\n", multiValue, field.getType().getName());

      			Processor<?> fieldProc = ProcessorsRepository.of(field.getType());
      			ArgumentInfo info = multiValue ? 
				new MultiArgumentInfo(bean, field, fieldProc) : 
				new SingleArgumentInfo(bean, field, fieldProc);

      			this.arguments.put(field.getName(), info);
    		}

  	}

  	public ArgumentInfo getArgInfo(String paramName) {
		if(!arguments.containsKey(paramName)) throw new RuntimeException(String.format("Field for parameter %s not found.", paramName));
	  	return arguments.get(paramName);
  	}
}
