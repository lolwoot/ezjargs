package com.lolwoot.ezjargs.processors;

import com.lolwoot.ezjargs.ArgumentLine;

public abstract class SingleProcessor<T> implements Processor<T> {
	
  public T process(ArgumentLine line) {
    T var = parse(line.next());
    System.out.println(String.format("Parsed variable: %s(%s).", var, var.getClass()));
    return var;
  }

  protected abstract T parse(String str);
}
 
