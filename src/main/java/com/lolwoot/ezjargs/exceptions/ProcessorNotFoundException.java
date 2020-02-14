package com.lolwoot.ezjargs.exceptions;

public class ProcessorNotFoundException extends CmdLineException {

	private static final String STR = "Processor for %s class not found.%n";

	public ProcessorNotFoundException(Class<?> clazz) {
		super(String.format(STR, clazz.getName()));
	}
}
