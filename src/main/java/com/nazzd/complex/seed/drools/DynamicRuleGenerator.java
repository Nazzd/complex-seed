package com.nazzd.complex.seed.drools;

import lombok.extern.slf4j.Slf4j;
import org.drools.template.ObjectDataCompiler;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.internal.io.ResourceFactory;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.UUID;

@Component
@Slf4j
public class DynamicRuleGenerator {

    private static final String RESOURCE_PATH = "src/main/resources/";

    private final KieServices kieServices = KieServices.Factory.get();

    public void generator(int i, Collection<?> ruleEntityList) {
        String s = applyRuleTemplate(ruleEntityList, "rules/debug.drt");

        System.out.println(s);


        createOrRefreshDrlInMemory(i, s);
    }


    private String applyRuleTemplate(Collection<?> ruleEntityList, String drtPath) {
        try {
            return new ObjectDataCompiler().compile(ruleEntityList,
                    ResourceFactory.newClassPathResource(drtPath).getInputStream());
        } catch (Exception e) {
            log.info("生成drools失败！");
            return "";
        }
    }

    private void createOrRefreshDrlInMemory(Integer i, String drl) {


        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        kieFileSystem.generateAndWritePomXML(RuleTools.getReleaseId(i));


        String path = RESOURCE_PATH + UUID.randomUUID().toString() + ".drl";

        kieFileSystem.write(path, drl);

        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem).buildAll();
        if (kieBuilder.getResults().hasMessages(Message.Level.ERROR)) {
            log.error("xxxxxxxxxxxx error");
            throw new IllegalArgumentException();
        }
    }


}
