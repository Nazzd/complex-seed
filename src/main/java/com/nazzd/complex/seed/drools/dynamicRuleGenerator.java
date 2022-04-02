package com.nazzd.complex.seed.drools;

import org.kie.api.KieServices;
import org.kie.api.internal.utils.KieService;
import org.springframework.stereotype.Component;

@Component
public class dynamicRuleGenerator {

    private static final String RESOURCE_PATH = "src/main/resources/";

    private final KieServices kieServices = KieServices.Factory.get();
}
