package com.lolwoot.ezjargs.processors;

import java.io.File;

public class FileProcessor extends AbstractProcessor<File> {
	public File parse(String str) {
		return new File(str);
	}
}
