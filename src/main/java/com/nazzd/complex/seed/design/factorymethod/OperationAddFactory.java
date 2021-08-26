package com.nazzd.complex.seed.design.factorymethod;

import com.nazzd.complex.seed.design.simplefactory.Operation;
import com.nazzd.complex.seed.design.simplefactory.OperationAdd;

public class OperationAddFactory implements IOperateFactory {


    @Override
    public Operation getOperate() {
        return new OperationAdd();
    }
}
