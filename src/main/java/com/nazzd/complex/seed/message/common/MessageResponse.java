package com.nazzd.complex.seed.message.common;

import lombok.Data;

@Data
public class MessageResponse<T extends AbstractMessageResponse> {

    private Long businessId;

    private int serviceType;

    private String message;

    private int status;

    private T data;
}
