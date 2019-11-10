package com.lolwoot.ezjargs;

public class Main1 {
	
	private String[] d;

	public static void main(String[] args) {
		args = new String[]{"-d", "StringValue#1", "StringValue#2"};

		Main1 m1 = new Main1();

		ArgumentsParser.parse(args, m1);

		System.out.println("Multiarguments field: " + args);
	}

}
