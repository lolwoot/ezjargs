package com.lolwoot.ezjargs;

import com.lolwoot.ezjargs.exceptions.FieldNotFoundException;
import com.lolwoot.ezjargs.exceptions.OptionNotMappedException;
import com.lolwoot.ezjargs.options.AbstractOption;
import com.lolwoot.ezjargs.options.MultiOption;
import com.lolwoot.ezjargs.options.SingleOption;
import com.lolwoot.ezjargs.processors.Processor;
import com.lolwoot.ezjargs.processors.ProcessorsRepository;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class BeanParser {

	private Object bean;
	
	Map<String, AbstractOption> options = new HashMap<>();
	
	public BeanParser(Object bean) {
		this.bean = bean;
  	}

  	public void parseBean(Map<String, String> bindMap) {
		Class<?> clazz = bean.getClass();

		for(Map.Entry<String, String> entry : bindMap.entrySet()) {

			Field field = null;
			try {
				field = clazz.getDeclaredField(entry.getKey());

				//check multivalue field
				boolean multiValue = field.getType().isArray();
				Processor<?> fieldProc = ProcessorsRepository.of(field.getType());
				AbstractOption opt = multiValue ?
						new MultiOption(bean, field, entry.getValue(), fieldProc) :
						new SingleOption(bean, field, entry.getValue(), fieldProc);

				this.options.put(entry.getValue(), opt);
			} catch (NoSuchFieldException ex) {
				System.err.printf("Field %s for mapping %s not found", entry.getKey(), entry.getValue());
				throw new FieldNotFoundException(String.format("Field: %s in %s not found.", entry.getKey(), bean.getClass().toString()));
			}
		}
	}

  	public AbstractOption getOptionByName(String optName) {
		if(!options.containsKey(optName)) {
			throw new OptionNotMappedException(optName);
		}
	  	return options.get(optName);
  	}
}
