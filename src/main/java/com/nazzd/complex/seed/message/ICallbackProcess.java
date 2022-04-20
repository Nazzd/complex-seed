package com.nazzd.complex.seed.message;

import com.nazzd.complex.seed.message.common.AbstractMessageResponse;
import com.nazzd.complex.seed.message.common.MessageResponse;

public interface ICallbackProcess<T extends AbstractMessageResponse> {

    void process(MessageResponse<T> response);
}
