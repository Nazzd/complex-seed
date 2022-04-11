package com.nazzd.complex.seed.drools.controller;

import com.nazzd.complex.seed.drools.DynamicRuleGenerator;
import com.nazzd.complex.seed.drools.RuleTools;
import com.nazzd.complex.seed.drools.entity.DrtEntity;
import com.nazzd.complex.seed.drools.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieServices;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;

@RestController
@Slf4j
@RequestMapping("/drool")
public class DroolSDecisionController {

    @Resource
    private DynamicRuleGenerator dynamicRuleGenerator;

    private static final KieServices KIE_SERVICES = KieServices.Factory.get();

    @GetMapping("/generator/1")
    public void generator1() {
        ArrayList<Object> objects = new ArrayList<>();
        DrtEntity build = DrtEntity.builder().ruleName("规则1").city("北京").build();

        objects.add(build);
        dynamicRuleGenerator.generator(1, objects);
    }

    @GetMapping("/generator/2")
    public void generator2() {
        ArrayList<Object> objects = new ArrayList<>();
        DrtEntity build = DrtEntity.builder().ruleName("规则2").city("常州").build();

        objects.add(build);
        dynamicRuleGenerator.generator(2, objects);
    }

    @GetMapping("/generator/3")
    public void generator3() {
        ArrayList<Object> objects = new ArrayList<>();
        DrtEntity build = DrtEntity.builder().ruleName("规则3").city("南京").build();

        objects.add(build);
        dynamicRuleGenerator.generator(3, objects);
    }

    @GetMapping("/execute/{type}")
    public void test(@PathVariable Integer type,
                     @RequestParam("city") String city,
                     @RequestParam("name") String name) {
        User user = new User();
        user.setAddress(city);
        user.setName(name);
        StatelessKieSession statelessKieSession = KIE_SERVICES.newKieContainer(RuleTools.getReleaseId(type)).getKieBase().newStatelessKieSession();

        statelessKieSession.execute(user);
    }
}
