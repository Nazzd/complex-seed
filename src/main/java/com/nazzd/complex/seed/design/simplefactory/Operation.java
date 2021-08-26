package com.nazzd.complex.seed.design.simplefactory;

import lombok.Data;

@Data
public abstract class Operation {

    private double num1;

    private double num2;

    public abstract double getResult();
}
