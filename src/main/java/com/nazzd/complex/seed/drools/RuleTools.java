package com.nazzd.complex.seed.drools;

import org.kie.api.KieServices;
import org.kie.api.builder.ReleaseId;

public class RuleTools {

    private static final KieServices KIE_SERVICES = KieServices.Factory.get();

    public static ReleaseId getReleaseId(Integer type) {
        RuleTypeEnum ruleTypeEnum = RuleTypeEnum.fromInt(type);
        return KIE_SERVICES.newReleaseId(ruleTypeEnum.getGroupId(), ruleTypeEnum.getArtifactId(), ruleTypeEnum.getVersion());
    }
}
