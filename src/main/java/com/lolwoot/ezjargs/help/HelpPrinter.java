package com.lolwoot.ezjargs.help;

import java.util.Map;
import java.io.PrintStream;

import com.lolwoot.ezjargs.options.AbstractOption;

public class HelpPrinter {
	
	private PrintStream print = System.out;

	public void setOut(PrintStream printStream) {
		this.print = printStream;
	}

	public void print(String cmdName, Map<String, AbstractOption> options) {
		this.print.printf("Usage: %s [-options] [arguments].%n", cmdName);
		this.print.printf("Option:%n");
		for(Map.Entry<String, AbstractOption> entry : options.entrySet()) {
			this.print.print(optToString(entry.getValue()));
		}
	}


	private String optToString(AbstractOption opt) {
		return String.format("    %-15s %15s.%n", opt.getName(), opt.getUsage());
	}
}
