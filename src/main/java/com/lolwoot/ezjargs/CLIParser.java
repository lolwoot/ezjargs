package com.lolwoot.ezjargs;

import com.lolwoot.ezjargs.exceptions.OptionNotMappedException;
import com.lolwoot.ezjargs.help.HelpPrinter;
import com.lolwoot.ezjargs.options.AbstractOption;
import com.lolwoot.ezjargs.options.OptionDescription;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CLIParser {

	private static final CLIParser instance = new CLIParser();

	private CLIParser() {
	}

	private static final HelpPrinter help = new HelpPrinter();

	private MappingBuilder mapping;

	private static void parse(String[] args, Object bean, Map<String, OptionDescription.OptionDescriptionBuilder> mapping) {

		ParametersLine pLine = new ParametersLine(args);
		BeanParser beanParser = new BeanParser(bean);

		try {
			beanParser.parseBean(mapping);

			while (!pLine.isEmpty()) {
				String optionName = pLine.getToken();
				AbstractOption option = beanParser.getOptionByName(optionName);
				pLine.processOption(option);
			}
		} catch (OptionNotMappedException e) {
			//print help message if exception while find not mapped option
			help.print(bean.getClass().getSimpleName(), beanParser.options);
			throw e;
		}
	}

	public static MappingBuilder builder() {
		instance.mapping = new MappingBuilder();
		return instance.mapping;
	}

	public static final class MappingBuilder {

		private Map<String, OptionDescription.OptionDescriptionBuilder> map = new HashMap<>();

		public MappingBuilder bind(String fieldName, OptionDescription.OptionDescriptionBuilder optDB) {
			this.map.putIfAbsent(fieldName, optDB);
			return this;
		}

		public MappingBuilder bind(String optionName, String optionFieldName, String description) {
			OptionDescription.OptionDescriptionBuilder optionDescription = OptionDescription.newBuilder()
					.name(optionName)
					.fieldName(optionFieldName)
					.description(description);
			bind(optionFieldName, optionDescription);
			return this;
		}

		public MappingBuilder bind(String optionName, String optionFieldName) {
			OptionDescription.OptionDescriptionBuilder optionDescription = OptionDescription.newBuilder()
					.name(optionName)
					.fieldName(optionFieldName);
			return bind(optionFieldName, optionDescription);
		}

		public MappingBuilder bindParameters(String parametersFieldName) {
			OptionDescription.OptionDescriptionBuilder optDB = OptionDescription.newBuilder()
					.name(ParametersLine.PARAMETERS_NAME)
					.fieldName(parametersFieldName);
			return bind(parametersFieldName, optDB);
		}

		public void parse(String[] args, Object bean) {
			CLIParser.parse(args, bean, Collections.unmodifiableMap(map));
		}
	}
}
