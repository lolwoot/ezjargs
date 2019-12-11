package com.lolwoot.ezjargs.exceptions;

public class OptionNotMappedException extends RuntimeException {
	public OptionNotMappedException(String optName) {
		super(String.format("Field mapping for [%s] option not found.", optName));
	}
}
