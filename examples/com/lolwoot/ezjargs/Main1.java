package com.lolwoot.ezjargs;

import java.io.File;
import java.util.Arrays;

public class Main1 {
	
	private File[] d;
	private File f;

	public static void main(String[] args) {
		args = new String[]{
			"-d", "C:\\dev\\props1.properties", "C:\\dev\\props2.properties",
			"-f", "C:\\dev\\props1.properties"
		};

		Main1 m1 = new Main1();

		CLIParser.parse(args, m1);

		System.out.println("Multiarguments field: " + Arrays.toString(m1.d));
		System.out.println("Equals : " + m1.d[0].equals(m1.f));
	}

}
