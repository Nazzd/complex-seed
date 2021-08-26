package com.nazzd.complex.seed.design.factorymethod;

import com.nazzd.complex.seed.design.simplefactory.Operation;
import com.nazzd.complex.seed.design.simplefactory.OperationSub;

public class OperationSubFactory implements IOperateFactory {


    @Override
    public Operation getOperate() {
        return new OperationSub();
    }
}
