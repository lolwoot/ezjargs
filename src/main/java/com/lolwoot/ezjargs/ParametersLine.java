package com.lolwoot.ezjargs;

import com.lolwoot.ezjargs.processors.Processor;
import com.lolwoot.ezjargs.options.AbstractOption;

public class ParametersLine {

	private String[] params;

	private int pt;

	public ParametersLine(String[] args) {
		this.params = args;
		this.pt = 0;
	}

	public void processOpt(AbstractOption opt) {
		
		System.out.println("Pointer before process: " + pt);

		//TODO test?
		opt.process(this);

		//increase current pt to next Option name
		this.pt++;

		System.out.println("Pointer after process: " + pt);
	}

	//return name of current parameter
    	public String peekOptName() {
	    	//in case when current point to additional parameter value TODO
	    	if(!isOption(params[pt])) {
			//TODO fix?!
			pt--;
			return "additional";
	    	}
	    
	    	String result = params[pt].replaceAll("-", "");
	    	return result;
    	}

    	public boolean isNextOption() {
		return isOption(params[pt + 1]);
    	}   

    	public String next() {
	    	return params[++pt];
    	}

    	public boolean hasNext() {
	    	return pt + 1 < params.length;
    	}

    	private boolean isOption(String str) {
	    	return str.indexOf("-") == 0;
    	}
 }

