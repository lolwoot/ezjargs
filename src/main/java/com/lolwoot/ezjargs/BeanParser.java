package com.lolwoot.ezjargs;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.HashMap;

import com.lolwoot.ezjargs.processors.Processor;
import com.lolwoot.ezjargs.processors.ProcessorsRepository;
import com.lolwoot.ezjargs.options.AbstractOption;
import com.lolwoot.ezjargs.options.MultiOption;
import com.lolwoot.ezjargs.options.SingleOption;
import com.lolwoot.ezjargs.help.HelpPrinter;
import com.lolwoot.ezjargs.exceptions.ProcessorNotFoundException;
import com.lolwoot.ezjargs.exceptions.OptionNotMappedException;

public class BeanParser {

	private Object bean;
	
	private Map<String, AbstractOption> options = new HashMap<>();

	private static final HelpPrinter help = new HelpPrinter();
	
	public BeanParser(Object bean, Map<String, String> bindMap) {
    
		this.bean = bean;
		Class<?> clazz = bean.getClass();

		for(Map.Entry<String, String> entry : bindMap.entrySet()) {

			Field field = null;
			try {
				field = clazz.getDeclaredField(entry.getKey());
				System.out.printf("Getting processor for %s.\n", entry);

				//check multivalue field
				boolean multiValue = field.getType().isArray();
				System.out.printf("Multivalue: %s. Field class: %s.\n", multiValue, field.getType().getName());
				Processor<?> fieldProc = ProcessorsRepository.of(field.getType());
				AbstractOption opt = multiValue ? 
					new MultiOption(bean, field, entry.getValue(), fieldProc) : 
					new SingleOption(bean, field, entry.getValue(), fieldProc);

				this.options.put(entry.getValue(), opt);
			} catch (NoSuchFieldException ex) {
				//TODO
				System.out.println(ex.getMessage());
				ex.printStackTrace();
			}
		}

		//TODO
		help.print(bean.getClass().getName(), options);		
		
		System.out.printf("Parsed fileds: %s\n", options);
  	}

  	public AbstractOption getOptionByName(String optName) {
		if(!options.containsKey(optName)) {
			throw new OptionNotMappedException(optName);
		}
	  	return options.get(optName);
  	}
}
