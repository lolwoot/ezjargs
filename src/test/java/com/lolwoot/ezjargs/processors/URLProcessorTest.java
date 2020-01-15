package com.lolwoot.ezjargs.processors;

import com.lolwoot.ezjargs.exceptions.ProcessorParsingException;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class URLProcessorTest {

    private static final URLProcessor processor = new URLProcessor();

    @Test
    public void simpleUrlTest() throws MalformedURLException {

        URL EXPECTED = new URL("http", "localhost", 8080, "/test/123/321");

        URL result = processor.parse("http://localhost:8080/test/123/321");

        assertEquals(EXPECTED, result);
    }

    @Test
    public void simpleUrlWrongTest() {

        assertThrows(ProcessorParsingException.class, () -> {
            processor.parse("localhost");
        });
    }

}
