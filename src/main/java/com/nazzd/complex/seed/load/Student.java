package com.nazzd.complex.seed.load;

import lombok.Data;

@Data
public class Student extends AbstractResult {

    private Long id;

    private String name;

    private Integer age;

    private Boolean sex;
}
