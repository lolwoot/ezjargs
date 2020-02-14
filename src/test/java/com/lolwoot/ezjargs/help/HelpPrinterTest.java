package com.lolwoot.ezjargs.help;

import com.lolwoot.ezjargs.CLIParser;
import com.lolwoot.ezjargs.exceptions.OptionNotMappedException;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class HelpPrinterTest {

    @Test
    public void showHelpMessageWhenErrorOccurred() {
        final class Container {
            private String name;
            private Integer count;
            private File[] params;
        }

        Container c = new Container();

        assertThrows(OptionNotMappedException.class, () -> CLIParser
                .builder()
                .bind("-n", "name")
                .bind("-c", "count")
                .bindParameters("params")
                .parse(new String[]{
                        "-n", "name",
                        "-c", "123",
                        //try to insert
                        "-not_existing_option", "value",
                        "C:\\file1.txt", "C:\\file2.txt", "C:\\file3.txt"
                }, c));
    }

    @Test
    public void showHelpMessageWhenErrorOccurredWithDescription() {
        final class Container {
            private String name;
            private Integer count;
            private File[] params;
        }

        Container c = new Container();

        assertThrows(OptionNotMappedException.class, () -> CLIParser
                .builder()
                .bind("-n", "name", "Simple name")
                .bind("-c", "count", "Count")
                .bindParameters("params")
                .parse(new String[]{
                        "-n", "name",
                        "-c", "123",
                        //try to insert
                        "-not_existing_option", "value",
                        "C:\\file1.txt", "C:\\file2.txt", "C:\\file3.txt"
                }, c));
    }

}
