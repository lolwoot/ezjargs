package com.lolwoot.ezjargs;

import com.lolwoot.ezjargs.exceptions.FieldNotFoundException;
import com.lolwoot.ezjargs.exceptions.OptionNotMappedException;
import com.lolwoot.ezjargs.options.AbstractOption;
import com.lolwoot.ezjargs.options.OptionDescription;
import com.lolwoot.ezjargs.processors.ProcessorsRepository;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BeanParser {

    private Object bean;

    Map<String, AbstractOption> options = new HashMap<>();

    public BeanParser(Object bean) {
        this.bean = bean;
    }

    public void parseBean(Map<String, OptionDescription.OptionDescriptionBuilder> bindMap) {
        Class<?> clazz = bean.getClass();

        for (Map.Entry<String, OptionDescription.OptionDescriptionBuilder> entry : bindMap.entrySet()) {

            Field field = null;
            try {
                field = clazz.getDeclaredField(entry.getKey());

                //build new option description
                //TODO refactor check array or collection in builder?
                OptionDescription.OptionDescriptionBuilder builder = entry.getValue();
                builder.multi(isArrayOrCollection(field));
                OptionDescription optD = builder.build();

                AbstractOption opt = optD.createOption(bean, field, ProcessorsRepository.of(field.getType()));

                this.options.put(opt.getOptInfo().getName(), opt);
            } catch (NoSuchFieldException ex) {
                System.err.printf("Field %s for mapping %s not found", entry.getKey(), entry.getValue());
                throw new FieldNotFoundException(String.format("Field: %s in %s not found.", entry.getKey(), bean.getClass().toString()));
            }
        }
    }

    private boolean isArrayOrCollection(Field field) {
        Class<?> clazz = field.getType();
        return clazz.isArray() || clazz.isInstance(Collection.class);
    }

    public AbstractOption getOptionByName(String optName) {
        if (!options.containsKey(optName)) {
            throw new OptionNotMappedException(optName);
        }
        return options.get(optName);
    }
}
