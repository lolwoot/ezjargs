package com.lolwoot.ezjargs.options;

import com.lolwoot.ezjargs.processors.Processor;

import java.lang.reflect.Field;
import java.util.Objects;

//TODO check immutability and builder
public final class OptionDescription {

    private final String name;
    private final String optionFieldName;
    private final String description;

    private final boolean multi;
    private final boolean mandatory;

    //TODO
    private final Type type;

    private OptionDescription(String name, String optionFieldName, String description, boolean multi, boolean mandatory, Type type) {
        this.name = name;
        this.optionFieldName = optionFieldName;
        this.description = description;
        this.multi = multi;
        this.type = type;
        this.mandatory = mandatory;
    }

    public static OptionDescriptionBuilder newBuilder() {
        return new OptionDescriptionBuilder();
    }

    public String getName() {
        return name;
    }

    public String getOptionFieldName() {
        return optionFieldName;
    }

    public String getDescription() {
        return description;
    }

    public boolean isMulti() {
        return multi;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public AbstractOption createOption(Object bean, Field field, Processor<?> processor) {
        return this.type.create(bean, field, processor, this);
    }

    private enum Type {
        SINGLE {
            @Override
            AbstractOption create(Object bean, Field field, Processor<?> processor, OptionDescription optD) {
                return new SingleOption(bean, field, processor, optD);
            }
        },
        MULTI {
            @Override
            AbstractOption create(Object bean, Field field, Processor<?> processor, OptionDescription optD) {
                return new MultiOption(bean, field, processor, optD);
            }
        };

        abstract AbstractOption create(Object bean, Field field, Processor<?> processor, OptionDescription optD);
    }

    public static final class OptionDescriptionBuilder {

        //mandatory
        private String name;
        private String optionFieldName;
        private Type type = Type.SINGLE;

        //optional
        private String description = "<empty-description>";

        //default values for boolean class members - false
        private boolean multi;
        private boolean mandatory;

        public OptionDescriptionBuilder name(String name) {
            this.name = name;
            return this;
        }

        public OptionDescriptionBuilder fieldName(String fieldName) {
            this.optionFieldName = fieldName;
            return this;
        }

        public OptionDescriptionBuilder description(String description) {
            this.description = description;
            return this;
        }

        public OptionDescriptionBuilder multi(boolean multi) {
            this.multi = multi;
            this.type = multi ? Type.MULTI : Type.SINGLE;
            return this;
        }

        public OptionDescriptionBuilder mandatory(boolean mandatory) {
            this.mandatory = mandatory;
            return this;
        }

        public OptionDescription build() {
            Objects.requireNonNull(name);
            Objects.requireNonNull(optionFieldName);
            return new OptionDescription(name, optionFieldName, description, multi, mandatory, type);
        }

    }
}
