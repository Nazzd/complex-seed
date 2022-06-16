package com.nazzd.complex.seed.bo;

import lombok.Data;

import javax.validation.Valid;

@Data
@Valid
public class UserAdd {

    private String name;

    private String description;

    private Integer isEnable;
}
