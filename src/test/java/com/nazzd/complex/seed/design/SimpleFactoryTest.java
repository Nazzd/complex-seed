package com.nazzd.complex.seed.design;

import com.nazzd.complex.seed.design.simplefactory.Operation;
import com.nazzd.complex.seed.design.simplefactory.OperationFactory;
import org.junit.jupiter.api.Test;

public class SimpleFactoryTest {

    @Test
    public void simpleFactoryTest(){
        Operation operate = OperationFactory.createOperate("-");
        operate.setNum1(1);
        operate.setNum2(3);
        double result = operate.getResult();
        System.out.println(result);

    }
}
