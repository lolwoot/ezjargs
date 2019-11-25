package com.lolwoot.ezjargs;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.HashMap;

import com.lolwoot.ezjargs.processors.Processor;
import com.lolwoot.ezjargs.processors.ProcessorsRepository;
import com.lolwoot.ezjargs.options.AbstractOption;
import com.lolwoot.ezjargs.options.MultiOption;
import com.lolwoot.ezjargs.options.SingleOption;

public class BeanParser {

	private Object bean;
	
	private Map<String, AbstractOption> options = new HashMap<>();
	
	public BeanParser(Object bean) {
    
		this.bean = bean;  
		Class<?> clazz = bean.getClass();

    		Field[] fields = clazz.getDeclaredFields();
	    	for(Field field : fields) {
      			System.out.printf("Getting processor for %s.\n", field.getName());

			//check multivalue field
			boolean multiValue = field.getType().isArray();
			System.out.printf("Multivalue: %s. Field class: %s.\n", multiValue, field.getType().getName());

      			Processor<?> fieldProc = ProcessorsRepository.of(field.getType());
      			AbstractOption opt = multiValue ? 
				new MultiOption(bean, field, fieldProc) : 
				new SingleOption(bean, field, fieldProc);

      			this.options.put(field.getName(), opt);
    		}
		System.out.printf("Parsed fileds: %s\n", options);
  	}

  	public AbstractOption getOptionByName(String name) {
		if(!options.containsKey(name)) throw new RuntimeException(String.format("Field for parameter %s not found.", name));
	  	return options.get(name);
  	}
}
