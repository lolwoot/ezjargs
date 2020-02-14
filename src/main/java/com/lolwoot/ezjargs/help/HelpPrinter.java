package com.lolwoot.ezjargs.help;

import com.lolwoot.ezjargs.options.AbstractOption;
import com.lolwoot.ezjargs.options.OptionDescription;

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
		for (Map.Entry<String, AbstractOption> entry : options.entrySet()) {
			this.print.print(optToString(entry.getValue().getOptInfo()));
		}
	}


	private String optToString(OptionDescription opt) {
		return String.format("\t%-15s%5s\t%-40s%n", opt.getName(), containingOptionValueString(opt), opt.getDescription());
	}

	private String containingOptionValueString(OptionDescription option) {
		StringBuilder sb = new StringBuilder();

		if (option.isMulti()) sb.append("L");
		if (option.isMandatory()) sb.append("M");

		return sb.toString();
	}
}
