package com.nazzd.complex.seed.message;

import com.nazzd.complex.seed.message.common.AbstractMessageRequest;
import com.nazzd.complex.seed.message.common.AbstractMessageResponse;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class MessageFactory {

    public static Map<Integer, AbstractMessageResponse> messageResponseCommandMap = new ConcurrentHashMap<>();
    public static Map<Class<? extends AbstractMessageResponse>, AbstractMessageResponse> messageResponseClassCommandMap = new ConcurrentHashMap<>();
    public static Map<Integer, AbstractMessageRequest> messageRequestCommandMap = new ConcurrentHashMap<>();
    public static Map<Class<? extends AbstractMessageRequest>, AbstractMessageRequest> messageRequestClassCommandMap = new ConcurrentHashMap<>();


    static {
        try {
            Reflections reflections = new Reflections("");
            Set<Class<? extends AbstractMessageRequest>> classes = reflections.getSubTypesOf(AbstractMessageRequest.class);
            classes.forEach(aClass -> {
                try {
                    AbstractMessageRequest instance = aClass.newInstance();
                    int serviceType = instance.getServiceType();
                    if (messageRequestCommandMap.containsKey(serviceType)) {
                        log.warn("{}实现类的serviceType已经存在,发生冲突,请修改后再使用", instance.getClass().getName());
                    }
                    messageRequestCommandMap.put(serviceType, instance);
                    messageRequestClassCommandMap.put(instance.getClass(), instance);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            });

            Set<Class<? extends AbstractMessageResponse>> classez = reflections.getSubTypesOf(AbstractMessageResponse.class);
            classez.forEach(aClass -> {
                try {
                    AbstractMessageResponse instance = aClass.newInstance();
                    int serviceType = instance.getServiceType();
                    if (messageResponseCommandMap.containsKey(serviceType)) {
                        log.warn("{}实现类的serviceType已经存在,发生冲突,请修改后再使用", instance.getClass().getName());
                    }
                    messageResponseCommandMap.put(serviceType, instance);
                    messageResponseClassCommandMap.put(instance.getClass(), instance);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            log.warn("初始化失败!");
            System.exit(1);
        }
    }

    public static AbstractMessageResponse getResponseCommand(Class<? extends AbstractMessageResponse> clasz) {
        return messageResponseClassCommandMap.get(clasz);
    }
}
