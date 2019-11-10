package com.lolwoot.ezjargs.processors;

import java.util.HashMap;
import java.util.Map;

import java.io.File;

public class ProcessorsRepository {
  
  private static Map<Class<?>, Processor<?>> map = new HashMap<>();

  //Default parameters handlers
  static {
    map.put(Integer.class, new IntegerProcessor());
    map.put(String.class, new StringProcessor());
    map.put(File.class, new FileProcessor());
  }

  public static Processor<?> of(Class<?> clazz) {
	  if(clazz.isArray()) {
		  clazz = clazz.getComponentType();
	  }
	  
	  System.out.println(String.format("Getting processor for class(%s).\n", clazz));
	  
	  if(!map.containsKey(clazz)) throw new RuntimeException(String.format("Missing custom processor for %s.\n", clazz.getName()));
	  
	  return map.get(clazz);
  }

}
