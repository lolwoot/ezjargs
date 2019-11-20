package com.lolwoot.ezjargs.processors;

public class IntegerProcessor extends AbstractProcessor<Integer> {

	public Integer parse(String str) {
		return Integer.parseInt(str);
	}
}
  
