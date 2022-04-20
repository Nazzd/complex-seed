package com.nazzd.complex.seed.message.handle;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nazzd.complex.seed.message.IMessageProcessor;
import com.nazzd.complex.seed.message.common.AbstractMessageRequest;
import com.nazzd.complex.seed.message.common.AbstractMessageResponse;
import com.nazzd.complex.seed.message.common.MessageRequest;
import com.nazzd.complex.seed.message.common.MessageResponse;
import com.nazzd.complex.seed.message.enums.ServiceTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;
import java.util.Objects;

@Slf4j
@Component
public class MessageProcessor implements IMessageProcessor {

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Resource
    private MessageHandleService messageHandleService;

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public void sendMessageASync(AbstractMessageRequest request, Long businessId) {
        MessageRequest.builder()
                .businessId(businessId)
                .serviceType(request.getServiceType())
                .data(request);

    }

    @Override
    public void sendMessageASync(MessageRequest<AbstractMessageRequest> request) {
        String topic = "";
        String json;
        try {
            json = OBJECT_MAPPER.writeValueAsString(request);
        } catch (JsonProcessingException e) {
            log.error("序列化错误{}", e.getMessage(), e);
            throw new RuntimeException("");
        }
        Message<String> message = MessageBuilder.withPayload(json)
                .setHeader(KafkaHeaders.TOPIC, topic).build();

        kafkaTemplate.send(message).addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                log.info("写入kafka成功,topic:{}", topic);
            }

            @Override
            public void onFailure(Throwable throwable) {
                log.error("发送kafka:topic{}产生异常信息{}", topic, throwable);
            }
        });
    }

    @KafkaListener(id = "", topics = "")
    public void receiveMessage(String json, Acknowledgment acknowledgment) {
        log.info("[SEED]消费消息:{}", json);
        JSONObject jsonObject = JSONUtil.parseObj(json);
        MessageResponse messageResponse = JSONUtil.toBean(json, MessageResponse.class);

        Integer serviceType = jsonObject.getInt("serviceType");
        if (Objects.nonNull(ServiceTypeEnum.fromInt(serviceType))) {
            JSONObject data = jsonObject.getJSONObject("data");
            if (Objects.nonNull(data)) {
                AbstractMessageResponse response = data.toBean(ServiceTypeEnum.fromInt(serviceType).getMessageResponse());
                messageResponse.setData(response);
            }
        } else {
            log.warn("不支持的serviceType");
        }
        boolean handle = messageHandleService.handle(messageResponse);
        if (!handle) {
            log.warn("本次消息处理失败,serviceType:{},消息:{}", serviceType, json);
        }
        acknowledgment.acknowledge();

    }
}
