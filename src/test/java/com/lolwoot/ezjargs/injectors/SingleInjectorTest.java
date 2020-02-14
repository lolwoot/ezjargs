package com.lolwoot.ezjargs.injectors;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SingleInjectorTest {

    @Test
    public void simpleInjecting() {

        class Container {
            private int x;
        }

        Container c = new Container();

        SingleInjector injector = new SingleInjector(c, getField(Container.class, "x"));
        injector.inject(1111);

        assertEquals(1111, c.x);
    }

    private Field getField(Class<?> clazz, String field) {
        try {
            return clazz.getDeclaredField(field);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }

}