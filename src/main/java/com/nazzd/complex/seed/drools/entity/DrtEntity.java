package com.nazzd.complex.seed.drools.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DrtEntity {

    private String ruleName;

    private String city;
}
