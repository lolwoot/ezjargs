package com.lolwoot.ezjargs.help;

import java.io.File;

import com.lolwoot.ezjargs.CLIParser;
import com.lolwoot.ezjargs.help.HelpPrinter;

import org.junit.jupiter.api.Test;

public class HelpPrinterTest {

    @Test
    public void test() {
        final class Container {
            private String name;
            private Integer count;
            private File[] params;
        }

        Container c = new Container();

        CLIParser
				.builder()
                .bind("-n", "name")
                .bind("-c", "count")
                .bindParameters("params")
                .parse(new String[]{
                        "-n", "name",
                        "-c", "123",
                        "C:\\file1.txt", "C:\\file2.txt", "C:\\file3.txt"
                }, c);

    }

}
