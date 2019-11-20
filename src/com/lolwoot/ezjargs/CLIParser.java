package com.lolwoot.ezjargs;

import com.lolwoot.ezjargs.options.AbstractOption;

public class CLIParser {

	//Main function for client code
	public static void parse(String[] args, Object bean) {
		
		ParametersLine pLine = new ParametersLine(args);

		BeanParser beanParser = new BeanParser(bean);

		while(pLine.hasNext()) {
			String name = pLine.peekOptName();
			AbstractOption opt = beanParser.getOptionByName(name);

			pLine.processOpt(opt);
		}		
	}

}
