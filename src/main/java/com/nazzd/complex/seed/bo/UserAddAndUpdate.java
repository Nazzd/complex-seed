package com.nazzd.complex.seed.bo;

import lombok.Data;

import javax.validation.Valid;

@Data
public class UserAddAndUpdate {

    private String name;

    private String description;

    private Boolean isEnable;
}
