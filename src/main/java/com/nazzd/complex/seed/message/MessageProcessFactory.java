package com.nazzd.complex.seed.message;

import com.nazzd.complex.seed.message.common.AbstractMessageResponse;
import com.nazzd.complex.seed.utils.SeedSpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class MessageProcessFactory {

    private Map<Integer, ICallbackProcess> processMap = new ConcurrentHashMap();

    @PostConstruct
    public void init() {
        // 获取所有ICallbackProcess的实现类
        Map<String, ICallbackProcess> mappings = SeedSpringContextUtil.getBeansOfInterface(ICallbackProcess.class);

        mappings.forEach((s, iProcess) -> {
            ResolvableType resolvableType = ResolvableType.forInstance(iProcess);

            ResolvableType[] types = resolvableType.getInterfaces();
            for (ResolvableType resolvableType1 : types) {
                if (resolvableType1.toClass().equals(ICallbackProcess.class)) {
                    ResolvableType commandClass = resolvableType1.getGeneric(0);
                    Class<?> rawClass = commandClass.getRawClass();
                    if (Objects.nonNull(rawClass)) {
                        if (rawClass.getSuperclass().equals(AbstractMessageResponse.class)) {
                            Class<? extends AbstractMessageResponse> cla = (Class<? extends AbstractMessageResponse>) rawClass;
                            AbstractMessageResponse responseCommand = MessageFactory.getResponseCommand(cla);
                            processMap.put(responseCommand.getServiceType(), iProcess);
                        }

                    }
                }
            }
        });
    }

    public ICallbackProcess getICallbackProcess(int serviceType) {
        return processMap.get(serviceType);
    }


}
