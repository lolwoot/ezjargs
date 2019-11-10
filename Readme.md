# Small library that help to manage with command line arguments.
## Example of usage.
Simple programm with CLI interface.

`$ javac <options> <other_parameters>`

`$ programm -name arg1 -age 40`

````java
//TODO include imports

public class Container {
  
  private String name;  //Inject value1 String value

  private Integer age; //Inject 12 Integer value

  public Container(String[] args) {
    
    //Parse command line arguments and inject values.
    ArgumentLine.parse(this, args);

    //Now we can use parsed parameters like class members
    System.out.println(String.format("Name: %s, age: %d", name, age));
  }
}
````
