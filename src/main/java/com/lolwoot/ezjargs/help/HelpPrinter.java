package com.lolwoot.ezjargs.help;

import java.util.Map;
import java.io.PrintStream;

import com.lolwoot.ezjargs.options.AbstractOption;

public class HelpPrinter {
	
	private Map<String, AbstractOption> opt;

	private PrintStream print;

	public HelpPrinter(Map<String, AbstractOption> opt) {
		this.opt = opt;
		this.print = System.out;
	}

	public void setOut(PrintStream printStream) {
		this.print = printStream;
	}

	public void print() {
		for(Map.Entry<String, AbstractOption> entry : opt.entrySet()) {
			this.print.printf("%10s%10s.%n", entry.getKey(), "wtf");
		}
	}
}
