package com.lolwoot.ezjargs.processors;

public class StringProcessor extends SingleProcessor<String> {
  
  public String parse(String str) {
    System.out.println(String.format("Parsing parameter value: %s", str));
    return str; 
  }
}
