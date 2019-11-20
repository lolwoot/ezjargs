# Small library that help to manage with command line arguments.
Simple programm with CLI interface.

`$ javac <options> <other_parameters>`.

Single `<option>` contains `<-parameter [values]>`.

`<option>` may not have value like `$ programname -help`.


### Example of usage.

Simple example of CLI program that input 2 files and merge its to new one.

`$ filemerge <option> <other_parameters>`

`$ filemerge -files c:\\file1.txt c:\\file2.txt -out c:\\result.txt`.

````java
public class Container {
    
    private File[] files;
    
    private File out;
  
    public Container(String[] args) {
        //Parse command line arguments and inject values
        CLIParser.parse(this, args);

        //Now we can use parsed parameters like class members
        System.out.println(Arrays.toString(files));
  }
}
````

There is 2 option `-files` and `-out`. All option values will injected into filds with same name;

We also can use like this:

`$ filemerge -out c:\\result.txt c:\\file1.txt c:\\file2.txt`

Instead of using 2 options, we use one for result file( `-out` ) and `additional` vararg parameters at end of line for all other files.

````java
public class Container {
    
    private File[] additional;
    
    private File out;
  
    public Container(String[] args) {
        //Parse command line arguments and inject values
        CLIParser.parse(this, args);

        //Now we can use parsed parameters like class members
        System.out.println(Arrays.toString(files));
  }
}
````

All `<other parameters>` mapped to array field with name `additional`.

### Processors (TODO)
You can add custom processor for type you need. For example we want to add processor for `User` class.
````java
public class User {
	private String name;
	private LocalDate birth;
	//Constructor, getters and setters.
}
````
````java
public class UserProcessor extends AbstractProcessor<User> {
	protected User parse(String str) {
		String[] strs = str.split(" ");

		String name = strs[0];
		LocalDate date = LocalDate.of(strs[1]);
		
		return new User(name, date);
	}
}
````

`$ filemerge -out result.txt -user "Alex 2010-10-20" file1.txt file2.txt`
