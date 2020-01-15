package com.lolwoot.ezjargs.exceptions;

import com.lolwoot.ezjargs.processors.Processor;

public class ProcessorParsingException extends RuntimeException {
    public ProcessorParsingException(Processor<?> processor) {
        super(String.format("Exception while parsing with [%s] .", processor.getClass().getSimpleName()));
    }
}
