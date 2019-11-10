package com.lolwoot.ezjargs;

public class ArgumentsParser {

	//Main function for client code usage
	public static void parse(String[] args, Object bean) {
		
		ArgumentLine argsLine = new ArgumentLine(args);

		BeanParser beanParser = new BeanParser(bean, argsLine);

		while(argsLine.hasNext()) {
			String paramName = argsLine.peekParam();
			ArgumentInfo argInf = beanParser.getArgInfo(paramName);

			argsLine.processArg(argInf);
		}		
	}

}
