package com.lolwoot.ezjargs;

import com.lolwoot.ezjargs.options.AbstractOption;

import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

public class CLIParser {

	private static final CLIParser instance = new CLIParser();
	private CLIParser() {}

	private MappingBuilder mapping;

	private static void parse(String[] args, Object bean, Map<String, String> mapping) {
		
		ParametersLine pLine = new ParametersLine(args);

		BeanParser beanParser = new BeanParser(bean, mapping);

		while (!pLine.isEmpty()) {
			String optionName = pLine.getToken();
			AbstractOption option = beanParser.getOptionByName(optionName);
			pLine.processOption(option);
		}
	}

	public static MappingBuilder builder() {
		instance.mapping = new MappingBuilder();
		return instance.mapping;
	}

	public static final class MappingBuilder {
		private Map<String, String> map = new HashMap<>();
		public MappingBuilder bind(String optionName, String optionFieldName) {
			this.map.putIfAbsent(optionFieldName, optionName);
			return this;
		}
		public MappingBuilder bindParameters(String parametersFieldName) {
			return bind(ParametersLine.PARAMETERS_NAME, parametersFieldName);
		}
		public void parse(String[] args, Object bean) {
			CLIParser.parse(args, bean, Collections.unmodifiableMap(map));
		}
	}
}
