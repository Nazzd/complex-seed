package com.nazzd.complex.seed.design.simplefactory;

public class OperationFactory {

    public static Operation createOperate(String operate) {
        switch (operate) {
            case "+":
                return new OperationAdd();
            case "-":
                return new OperationSub();
            case "*":
                return new OperationMul();
            case "/":
                return new OperationDiv();
            default:
                throw new IllegalArgumentException("操作符不存在！");
        }
    }
}
