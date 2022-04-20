package com.nazzd.complex.seed.message.process;

import com.nazzd.complex.seed.message.ICallbackProcess;
import com.nazzd.complex.seed.message.common.MessageResponse;
import com.nazzd.complex.seed.message.response.ProductResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductCallProcess implements ICallbackProcess<ProductResponse> {

    @Override
    public void process(MessageResponse<ProductResponse> response) {
        log.info("处理具体的业务");

    }
}
