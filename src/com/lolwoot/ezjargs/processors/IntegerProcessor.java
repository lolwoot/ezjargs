package com.lolwoot.ezjargs.processors;

public class IntegerProcessor extends SingleProcessor<Integer> {
  
  public Integer parse(String str) {
    System.out.println(String.format("Parsing parameter value: %s.", str));
    return Integer.parseInt(str);
  }
}
  
