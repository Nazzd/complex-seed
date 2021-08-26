package com.nazzd.complex.seed.design.simplefactory;

public class OperationDiv extends Operation {

    @Override
    public double getResult() {
        if (this.getNum2() == 0) {
            throw new IllegalArgumentException("除数不能为0！");
        }
        return this.getNum1() / this.getNum2();
    }
}
