package com.nazzd.complex.seed.message.handle;

import com.nazzd.complex.seed.message.ICallbackProcess;
import com.nazzd.complex.seed.message.MessageProcessFactory;
import com.nazzd.complex.seed.message.common.MessageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
@Slf4j
public class MessageHandleService {

    @Resource
    private MessageProcessFactory messageProcessFactory;

    public boolean handle(MessageResponse messageResponse) {
        int serviceType = messageResponse.getServiceType();
        ICallbackProcess process = messageProcessFactory.getICallbackProcess(serviceType);
        if (Objects.nonNull(process)) {
            try {
                process.process(messageResponse);
            } catch (Exception e) {
                log.error("{}处理失败,返回结果:{},错误:{}", process.getClass().getSimpleName(), messageResponse, e);
            }
        } else {
            log.warn("不支持的serviceType:{}, 返回结果:{}", serviceType, messageResponse);
        }
        return true;
    }
}
