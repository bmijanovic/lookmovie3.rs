package com.ftn.sbnz.service.Helper;

import java.io.*;
import java.util.stream.Collectors;

import org.drools.decisiontable.ExternalSpreadsheetCompiler;
import org.drools.template.DataProvider;
import org.drools.template.DataProviderCompiler;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SessionBuilder {

    Logger logger = LoggerFactory.getLogger(SessionBuilder.class);

    private KieHelper kieHelper;
    public static final boolean loggingEnabled = false;

    public SessionBuilder() {
        this.kieHelper = new KieHelper();
    }

    public void addTemplate(String templatePath, String dataPath){
        try {
            File templateFile = new File(templatePath);
            File dataFile = new File(dataPath);

            InputStream template = new FileInputStream(templateFile);
            InputStream data = new FileInputStream(dataFile);

            ExternalSpreadsheetCompiler converter = new ExternalSpreadsheetCompiler();
            String rules = converter.compile(data, template, 2, 1);

            if (loggingEnabled) {
                logger.info(rules);
            }

            kieHelper.addContent(rules, ResourceType.DRL);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addRules(String path) {
        InputStream ruleStream = this.getClass().getResourceAsStream(path);
        String text = "";

        File file = new File(path);
        try {
            text = new BufferedReader(new FileReader(file)).lines().collect(Collectors.joining("\n"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (loggingEnabled) {
            logger.info(text);
        }

        kieHelper.addContent(text, ResourceType.DRL);
    }

    public KieSession build() {
        //STREAM mode
        return kieHelper.build(EventProcessingOption.STREAM).newKieSession();
    }
}