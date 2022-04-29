package com.nazzd.complex.seed.task.unit.handler;

import com.nazzd.complex.seed.task.Message;
import com.nazzd.complex.seed.task.unit.request.AbstractUnitReq;
import com.nazzd.complex.seed.task.unit.response.AbstractUnitResp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public abstract class AbstractHandler<T extends AbstractUnitReq, E extends AbstractUnitResp> {

    private T req;

    private E resp;

    public abstract String getUnitId();

    /**
     * 本单元支持的业务类型
     *
     * @return
     */
    public abstract List<Integer> getServiceType();

    /**
     * 支持哪种业务
     *
     * @return
     */
    public final boolean support() {
        return true;
    }

    public abstract E doHandle(Message<T> message) throws Exception;

    /**
     * 外部调用, 模板方法   支持业务超时,结果处理,投递下一单元
     *
     * @param message
     * @param miils
     */
    public void handle(Message message, Long miils) {
        try {
            /*FutureTask<Message.Context.UnitData> unitDataFutureTask = new FutureTask<>(() -> new Message.Context.UnitData().setData(doHandle(message)));
            unitDataFutureTask.run();
            Message.Context.UnitData unitData = unitDataFutureTask.get(miils, TimeUnit.MILLISECONDS);
            // 将结果填充到message中
            message = buildSuccessResult(message, unitData);*/

            // 首先判断是否存在下一个单元
            // 如果存在 则获取下一个单元的消息队列,然后进行投递
            // 如果不存在,则进行返回值封装,将结果投递出去

        } catch (Exception e) {
            // 单元任务处理失败

        }

    }

    private Message buildSuccessResult(Message message, Message.Context.UnitData unitData) {
        Map<String, Message.Context.UnitData> commonData = message.getContext().getCommonData();
        if (Objects.nonNull(commonData)) {
            commonData.put(getUnitId(), unitData);
        } else {
            HashMap<String, Message.Context.UnitData> map = new HashMap<>();
            map.put(getUnitId(), unitData);
            message.getContext().setCommonData(map);
        }
        return message;
    }


}
