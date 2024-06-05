package com.ftn.sbnz.service.Config;

import com.ftn.sbnz.service.Helper.SessionBuilder;
import org.drools.decisiontable.ExternalSpreadsheetCompiler;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.builder.model.KieBaseModel;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.definition.KiePackage;
import org.kie.api.definition.rule.Rule;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.conf.ClockTypeOption;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.internal.utils.KieHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.*;
import java.util.Collection;

@Configuration
@EnableTransactionManagement
public class KieSessionConfig {

    private KieContainer kieContainer;

    @Autowired
    public KieSessionConfig(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    @Bean
    public KieSession kieSession() {
        SessionBuilder sessionBuilder = new SessionBuilder();
        sessionBuilder.addRules("./kjar/src/main/resources/rules/forward/forward.drl");
        sessionBuilder.addRules("./kjar/src/main/resources/rules/forward/comment.drl");
        sessionBuilder.addRules("./kjar/src/main/resources/rules/forward/watched.drl");
        sessionBuilder.addRules("./kjar/src/main/resources/rules/forward/wishlist.drl");
        sessionBuilder.addRules("./kjar/src/main/resources/rules/cep/cep_genre_recommendation.drl");
        sessionBuilder.addRules("./kjar/src/main/resources/rules/cep/cep_global_recommendation.drl");
        sessionBuilder.addTemplate("./kjar/src/main/resources/rules/templates/cep-genre-template.drt", "./kjar/src/main/resources/rules/templates/cep-genre-template.xlsx");
        sessionBuilder.addTemplate("./kjar/src/main/resources/rules/templates/cep-global-template.drt", "./kjar/src/main/resources/rules/templates/cep-global-template.xlsx");

        return sessionBuilder.build();
    }
    @Bean
    public KieSession kieSessionCep() {

        return kieContainer.newKieSession("cepKsession");
    }
}