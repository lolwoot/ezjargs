package com.lolwoot.ezjargs.processors;

import com.lolwoot.ezjargs.exceptions.ProcessorParsingException;
import com.sun.webkit.network.URLs;

import java.net.MalformedURLException;
import java.net.URL;

public class URLProcessor extends AbstractProcessor<URL> {
    @Override
    protected URL parse(String str) {
        try {
            return URLs.newURL(str);
        } catch (MalformedURLException e) {
            throw new ProcessorParsingException(this);
        }
    }
}
