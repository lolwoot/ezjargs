package com.lolwoot.ezjargs.options;

import com.lolwoot.ezjargs.ParametersLine;
import com.lolwoot.ezjargs.injectors.AbstractInjector;
import com.lolwoot.ezjargs.injectors.SingleInjector;
import com.lolwoot.ezjargs.processors.Processor;

import java.lang.reflect.Field;


public class SingleOption extends AbstractOption {

    public SingleOption(Object bean, Field field, Processor<?> processor, OptionDescription optD) {
        super(bean, field, processor, optD);
    }

    public Object processOpt(ParametersLine line) {
        return processor.process(line);
    }

    protected AbstractInjector createInjector(Object bean, Field field) {
        return new SingleInjector(bean, field);
    }
}
