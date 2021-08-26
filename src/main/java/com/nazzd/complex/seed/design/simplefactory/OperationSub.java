package com.nazzd.complex.seed.design.simplefactory;

public class OperationSub extends Operation {
    
    @Override
    public double getResult() {
        return this.getNum1() - this.getNum2();
    }
}
