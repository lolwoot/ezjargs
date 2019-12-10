package com.lolwoot.ezjargs;

import com.lolwoot.ezjargs.CLIParser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SimpleTest {

	@Test
	public void oneOptionAnd1OptValueString() {

		final class Container {
			private String str;
		}

		final String[] ARGS = {"-str", "A-string"};
		Container c = new Container();

		CLIParser.parse(ARGS, c);

		assertEquals(ARGS[1], c.str);
	}

	@Test
	public void twoOptionsAnd2OptValue() {
		
		final class Container {
			private String str1;
			private String str2;
		}

		final String[] ARGS = {"-str1", "FirstString", "-str2", "SecondString"};

		Container c = new Container();

		CLIParser.parse(ARGS, c);

		assertEquals(ARGS[1], c.str1);
		assertEquals(ARGS[3], c.str2);
	}

	@Test
	public void optionValueWithMissingProcessor() {
		
		final class Container {
			//we do not have processor for OptionClass.class
			private OptionClass opt;
		}

		final String[] ARGS = {"-opt", "value"};

		Container c = new Container();
		
		RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
			CLIParser.parse(ARGS, c);
		});

		assertTrue(thrown.getMessage().contains("Missing processor for"));
	}

	private class OptionClass {

	}
}
