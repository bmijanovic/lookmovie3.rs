package com.ftn.sbnz.service.Config;

import org.drools.decisiontable.ExternalSpreadsheetCompiler;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.ReleaseId;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.StringReader;

@Configuration
@EnableTransactionManagement
public class KieSessionConfig {

    private final KieContainer kieContainer;

    @Autowired
    public KieSessionConfig(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    @Bean
    public KieSession kieSession()
    {
        KieSession kieSession = null;
        try {
            File templateFile = new File("./kjar/src/main/resources/rules/templates/cep-genre-template.drt");
            File dataFile = new File("./kjar/src/main/resources/rules/templates/cep-genre-template.xlsx");

            InputStream template = new FileInputStream(templateFile);
            InputStream data = new FileInputStream(dataFile);

            ExternalSpreadsheetCompiler converter = new ExternalSpreadsheetCompiler();
            String drl = converter.compile(data, template, 2, 1);

            System.out.println(drl);

            KieServices kieServices = KieServices.Factory.get();
            KieFileSystem kfs = kieServices.newKieFileSystem();

            kfs.write("src/main/resources/rules/templates/rules.drl", kieServices.getResources().newReaderResource(new StringReader(drl)));

            KieBuilder kieBuilder = kieServices.newKieBuilder(kfs);
            kieBuilder.buildAll();

            KieContainer kieContainer = kieServices.newKieContainer(kieServices.newReleaseId("com.ftn.sbnz", "kjar", "0.0.1-SNAPSHOT"));
            kieSession = kieContainer.newKieSession("ksession");

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (kieSession == null) {
            kieSession = kieContainer.newKieSession("ksession");
        }

        return kieSession;

    }
    @Bean
    public KieSession kieSessionCep() {
        return kieContainer.newKieSession("cepKsession");
    }
}