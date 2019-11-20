package com.lolwoot.ezjargs;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class Main2 {


	public static void main(String[] args) {

		CmdArgs bean = new CmdArgs();

		CLIParser.parse(args, bean);
		
		bean.doSmt();
		System.out.println(bean);
	}

	private static class CmdArgs {
		
		private File[] additional;
		private String value;

		public String toString() {
			return String.format("Files: %s,Value: %s", additional, value);
		}

		public void doSmt() {
			for(File f : additional) {
				System.out.println(f);
				try (FileWriter fWriter = new FileWriter(f); PrintWriter writer = new PrintWriter(fWriter)) {
					writer.print(value);
				}
				catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
