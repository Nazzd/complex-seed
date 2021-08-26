package com.nazzd.complex.seed.design.factorymethod;

import com.nazzd.complex.seed.design.simplefactory.Operation;
import com.nazzd.complex.seed.design.simplefactory.OperationMul;

public class OperationMulFactory implements IOperateFactory {


    @Override
    public Operation getOperate() {
        return new OperationMul();
    }
}
