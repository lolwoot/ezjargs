package com.lolwoot.ezjargs.help;

import com.lolwoot.ezjargs.options.AbstractOption;
import com.lolwoot.ezjargs.options.MultiOption;

import java.io.PrintStream;
import java.util.Map;

public class HelpPrinter {

	private PrintStream print = System.out;

	public void setOut(PrintStream printStream) {
		this.print = printStream;
	}

	public void print(String cmdName, Map<String, AbstractOption> options) {
		this.print.printf("Usage: %s [-option [option_value,[option_value]]] [parameters].%n", cmdName);
		this.print.printf("L - list value, M - mandatory option%n");
		this.print.printf("Option:%n");
		for(Map.Entry<String, AbstractOption> entry : options.entrySet()) {
			this.print.print(optToString(entry.getValue()));
		}
	}


	private String optToString(AbstractOption opt) {
		return String.format("\t%-15s%5s\t%-40s%n", opt.getName(), containingOptionValueString(opt), opt.getDescription());
	}

	private String containingOptionValueString(AbstractOption option) {
		boolean multi = option.getClass().equals(MultiOption.class);
		boolean mandatory = true;

		StringBuilder sb = new StringBuilder();

		//TODO enum
		if (multi) sb.append("L");
		if (mandatory) sb.append("M");

		return sb.toString();
	}
}
