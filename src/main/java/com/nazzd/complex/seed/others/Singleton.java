package com.nazzd.complex.seed.others;

import java.io.ObjectStreamException;
import java.io.Serializable;

public class Singleton implements Serializable {

    private static Singleton instance = new Singleton();

    private Singleton() {
        if (instance != null) {
            throw new RuntimeException("该实例已创建！");
        }
    }

    public static Singleton getInstance() {
        return Singleton.instance;
    }

    private Object readResolve() throws ObjectStreamException {
        // instead of the object we're on,
        // return the class variable INSTANCE
        return instance;
    }



}
