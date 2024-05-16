package com.ftn.sbnz.service.Config;

import com.ftn.sbnz.service.Entities.Models.Film;
import com.ftn.sbnz.service.Entities.Models.User;
import com.ftn.sbnz.service.Repositories.FilmRepository;
import com.ftn.sbnz.service.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.drools.decisiontable.ExternalSpreadsheetCompiler;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import org.drools.template.ObjectDataCompiler;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class KieSessionInitializer implements ApplicationRunner {

    @Autowired
    private final KieSession kieSession;

    @Autowired
    private final FilmRepository filmRepository;

    @Autowired
    private final UserRepository userRepository;

    public KieSessionInitializer(KieSession kieSession, FilmRepository filmRepository, UserRepository userRepository) {
        this.kieSession = kieSession;
        this.filmRepository = filmRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        System.out.println("Initializing KieSession");

        // Get all films
        for (Film f : filmRepository.findAll()) {
            kieSession.insert(f);
        }

        // Get all users
        for (User u : userRepository.findAll()) {
            kieSession.insert(u);
        }

        // Generate rules from template
        generateRulesFromTemplate();
    }

    private void generateRulesFromTemplate() {


    }
}
