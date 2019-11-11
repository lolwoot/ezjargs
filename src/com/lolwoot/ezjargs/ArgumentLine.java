package com.lolwoot.ezjargs;

import com.lolwoot.ezjargs.processors.Processor;

public class ArgumentLine {

    private String[] args;

    private int pt;

    public ArgumentLine(String[] args) {
      this.args = args;
      this.pt = 0;
    }

    public void processArg(ArgumentInfo arg) {
	if(!isParameter(args[pt]))
	       System.out.println(String.format("IllegalArgumentException %s not a parameter", args[pt]));

	//TODO test?
	arg.processArg(this);
    }

    //return name of parameter
    public String peekParam() {
      if(!isParameter(args[pt])) throw new RuntimeException(String.format("%s not parameter.", args[pt]));

      String result = args[pt].replaceAll("-", "");
      System.out.println("Peek returning value: " + result);
      
      return result;
    }

    public boolean isNextParameter() {
	    return isParameter(args[pt + 1]);
    }   

    public void proceed(int d) {
	    pt += d;
    }

    public String next() {
      return args[++pt];
    }

    public boolean hasNext() {
      return pt + 1 < args.length;
    }

    private boolean isParameter(String str) {
      return str.indexOf("-") == 0;
    }
 }

