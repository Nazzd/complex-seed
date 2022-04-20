package com.nazzd.complex.seed.message.enums;

import com.nazzd.complex.seed.message.common.AbstractMessageRequest;
import com.nazzd.complex.seed.message.common.AbstractMessageResponse;
import com.nazzd.complex.seed.message.request.ProductRequest;
import com.nazzd.complex.seed.message.response.ProductResponse;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum ServiceTypeEnum {

    PRODUCT(1, ProductRequest.class, ProductResponse.class);

    private int serviceType;

    private Class<? extends AbstractMessageRequest> messageRequest;

    private Class<? extends AbstractMessageResponse> messageResponse;

    public static Map<Integer, ServiceTypeEnum> cache = new ConcurrentHashMap<>();

    static {
        for (ServiceTypeEnum value : ServiceTypeEnum.values()) {
            cache.put(value.getServiceType(), value);
        }
    }

    ServiceTypeEnum(int serviceType, Class<? extends AbstractMessageRequest> messageRequest, Class<? extends AbstractMessageResponse> messageResponse) {
        this.serviceType = serviceType;
        this.messageRequest = messageRequest;
        this.messageResponse = messageResponse;
    }

    public int getServiceType() {
        return serviceType;
    }

    public Class<? extends AbstractMessageRequest> getMessageRequest() {
        return messageRequest;
    }

    public Class<? extends AbstractMessageResponse> getMessageResponse() {
        return messageResponse;
    }

    public static ServiceTypeEnum fromInt(int serviceType) {
        return cache.get(serviceType);
    }
}
