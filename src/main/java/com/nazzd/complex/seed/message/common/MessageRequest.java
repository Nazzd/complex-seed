package com.nazzd.complex.seed.message.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageRequest<T extends AbstractMessageRequest> {

    private Long businessId;

    private int serviceType;

    private T data;
}
