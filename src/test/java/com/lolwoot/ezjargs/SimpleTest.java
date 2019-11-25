package com.lolwoot.ezjargs;

import com.lolwoot.ezjargs.CLIParser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SimpleTest {

	@Test
	public void shouldAnswerWithTrue() {

		final String[] ARGS = {"-str", "A-string"};
		Container1 c1 = new Container1();

		CLIParser.parse(ARGS, c1);

		assertEquals(ARGS[1], c1.str);
	}
}

class Container1 {
	public String str;
}
