package com.lolwoot.ezjargs;

import java.io.File;

public class Main {

  //Bean field's TODO make annotated?
  private String name;
  private Integer id;
  
  //Array field for arguments (javac First.java Second.java - First.java and Second.java is parameters)
  //private File[] parameters;
  
  public static void main(String[] args) {
	  
	  args = new String[] {
		  "-name", "lolwoot",
		  "-id", "12",
	  };

    Main main = new Main();

    CLIParser.parse(args, main);

    System.out.printf("Bean members: %s, %s.\n", main.name, main.id);
    //System.out.printf("Parameters: %s.\n", main.parameters.toString());
  }

}
