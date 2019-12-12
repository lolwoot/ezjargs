package com.lolwoot.ezjargs;

import com.lolwoot.ezjargs.exceptions.OptionNotMappedException;
import com.lolwoot.ezjargs.exceptions.ProcessorNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleTests {

	@Test
	public void oneOptionAnd1OptValueString() {
		
		final class Container {
			private String stringValue;
		}

		final String[] ARGS = {"-s", "string_value_option_value"};
		Container c = new Container();

		//CLIParser.parse(ARGS, c);
		CLIParser
			.bind("-s", "stringValue")
			.parse(ARGS, c);

		assertEquals(ARGS[1], c.stringValue);

	}

	@Test
	public void twoOptionsAnd2OptValue() {
		
		final class Container {
			private String str1;
			private String str2;
			public String toString() {
				return String.format("[%s, %s]", str1, str2);
			}
		}

		final String[] ARGS = {"-s", "FirstString", "-o", "SecondString"};

		Container c = new Container();

		CLIParser
			.bind("-s", "str1")
			.bind("-o", "str2")
			.parse(ARGS, c);

		System.out.println(c);

		assertEquals(ARGS[1], c.str1);
		assertEquals(ARGS[3], c.str2);
	}

	@Test
	public void parseWithEmptyBinding() {
		final String[] ARGS = {"-s", "test"};

		assertThrows(OptionNotMappedException.class, () -> {
			CLIParser.parse(ARGS, new Object());
		});
	}

	@Test
	public void optionValueWithMissingProcessor() {
		
		final class Container {
			//we do not have processor for OptionClass.class
			private OptionClass option;
		}

		final String[] ARGS = {"-opt", "value"};

		Container c = new Container();
		
		assertThrows(ProcessorNotFoundException.class, () -> {
			CLIParser
				.bind("-opt", "option")
				.parse(ARGS, c);
		});
	}
	private class OptionClass {

	}

	@Test
	public void onlyParameters() {
		final class Container {
			private String[] parameters;
		}

		final String[] ARGS = {"string1", "string2", "string3"};
		Container c = new Container();

		CLIParser
			.bindParameters("parameters")
			.parse(ARGS, c);

		assertArrayEquals(ARGS, c.parameters);
	}

	@Test
	public void optionsAndParameters() {
		final class Container {
			private String stringField;
			private Integer integerField;
			private String[] additional;
		}

		final String[] ARGS = {"-s", "string1", "-o", "123", "param1", "param2"};
		Container c = new Container();

		CLIParser
			.bind("-s", "stringField")
			.bind("-o", "integerField")
			.bindParameters("additional")
			.parse(ARGS, c);

		assertEquals("string1", c.stringField);
		assertEquals(Integer.valueOf(123), c.integerField);
		assertArrayEquals(new String[]{"param1", "param2"}, c.additional);

	}

	@Test
	public void soloParameterWithoutOptions() {
		final class Container {
			private String parameter;
		}

		final String[] ARGS = {"string1"};
		Container c = new Container();

		CLIParser
			.bindParameters("parameter")
			.parse(ARGS, c);
		
		assertEquals(ARGS[0], c.parameter);
	}

	@Test
	public void manyParametersWithoutOptions() {
		final class Container {
			private String[] parameters;
		}

		final String[] ARGS = {"string1", "string2", "string3"};
		Container c = new Container();

		CLIParser
			.bindParameters("parameters")
			.parse(ARGS, c);

		assertArrayEquals(ARGS, c.parameters);
	}

	@Test
	public void emptyLineWithoutOptionsAndParameters() {
		CLIParser.parse(new String[]{}, new Object());
	}
}