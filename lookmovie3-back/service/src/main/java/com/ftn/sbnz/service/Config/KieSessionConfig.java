package com.ftn.sbnz.service.Config;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class KieSessionConfig {

    private final KieContainer kieContainer;

    @Autowired
    public KieSessionConfig(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    @Bean
    public KieSession kieSession() {
        return kieContainer.newKieSession("ksession");
    }
    @Bean
    public KieSession kieSessionCep() {
        return kieContainer.newKieSession("cepKsession");
    }
}