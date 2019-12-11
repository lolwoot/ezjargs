package com.lolwoot.ezjargs;

import com.lolwoot.ezjargs.CLIParser;
import com.lolwoot.ezjargs.exceptions.ProcessorNotFoundException;
import com.lolwoot.ezjargs.exceptions.OptionNotMappedException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class SimpleTest {

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
}
