package com.nazzd.complex.seed;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestProxy {

    public static void main(String[] args) {
        People people = new Teather();
        People instance = (People)Proxy.newProxyInstance(people.getClass().getClassLoader(), people.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                String name = method.getName();

                System.out.println("============="+name);
                return method.invoke(people, args);
            }
        });
        instance.work();
    }
}
