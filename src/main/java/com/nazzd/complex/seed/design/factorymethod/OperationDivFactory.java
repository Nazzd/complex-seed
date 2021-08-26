package com.nazzd.complex.seed.design.factorymethod;

import com.nazzd.complex.seed.design.simplefactory.Operation;
import com.nazzd.complex.seed.design.simplefactory.OperationDiv;

public class OperationDivFactory implements IOperateFactory {


    @Override
    public Operation getOperate() {
        return new OperationDiv();
    }
}
