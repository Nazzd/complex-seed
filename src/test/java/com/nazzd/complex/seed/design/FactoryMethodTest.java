package com.nazzd.complex.seed.design;

import com.nazzd.complex.seed.design.factorymethod.OperationSubFactory;
import com.nazzd.complex.seed.design.simplefactory.Operation;
import org.junit.jupiter.api.Test;

public class FactoryMethodTest {

    @Test
    public void factoryMethodTest() {
        OperationSubFactory operationSubFactory = new OperationSubFactory();
        Operation operate = operationSubFactory.getOperate();
        operate.setNum1(2);
        operate.setNum2(3);
        double result = operate.getResult();
        System.out.println("result = " + result);


    }
}
