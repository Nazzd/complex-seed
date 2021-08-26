package com.nazzd.complex.seed.others;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class TestMain {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Singleton instance = Singleton.getInstance();

        Constructor<Singleton> declaredConstructor =
                Singleton.class.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        Singleton singleton = declaredConstructor.newInstance();
        System.out.println(instance==singleton);
    }
}
