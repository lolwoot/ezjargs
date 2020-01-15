package com.lolwoot.ezjargs.exceptions;

public class OptionNotMappedException extends CmdLineException {
	public OptionNotMappedException(String optName) {
		super(String.format("Field binding for [%s] option not found.", optName));
	}
}
