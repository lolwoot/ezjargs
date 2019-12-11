package com.lolwoot.ezjargs;

import com.lolwoot.ezjargs.options.AbstractOption;

import java.util.Map;
import java.util.HashMap;

public class CLIParser {

	//TODO
	private static final CLIParser instance = new CLIParser();
	private CLIParser() {}
	private static final Mapping mapping = new Mapping();

	//Main function for client code
	public static void parse(String[] args, Object bean) {
		
		ParametersLine pLine = new ParametersLine(args);

		BeanParser beanParser = new BeanParser(bean, mapping.create());

		while(pLine.hasNext()) {
			String name = pLine.peekOptName();
			AbstractOption opt = beanParser.getOptionByName(name);

			pLine.processOpt(opt);
		}		
	}

	//TODO binding
	public static CLIParser bind(String optionName, String fieldName) {
		//bindMap.put(fieldName, optionName);
		mapping.bind(optionName, fieldName);
		return instance;
	}

	private static class Mapping {
		private Map<String, String> tmpMap = new HashMap<>();
		
		void bind(String optionName, String fieldName) {
			this.tmpMap.put(fieldName, optionName);
		}

		Map<String, String> create() {
			HashMap<String, String> retMap = new HashMap<>(tmpMap);
			this.tmpMap.clear();
			return retMap;
		}

	}
	
}
