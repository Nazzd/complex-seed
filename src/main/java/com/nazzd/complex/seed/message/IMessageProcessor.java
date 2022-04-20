package com.nazzd.complex.seed.message;

import com.nazzd.complex.seed.message.common.AbstractMessageRequest;
import com.nazzd.complex.seed.message.common.MessageRequest;

public interface IMessageProcessor {

    void sendMessageASync(AbstractMessageRequest request, Long businessId);

    void sendMessageASync(MessageRequest<AbstractMessageRequest> request);
}
