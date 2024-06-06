package com.ftn.sbnz.service.Config;

import com.ftn.sbnz.service.Entities.Models.BWItem;
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
import org.kie.api.builder.KieModule;
import org.kie.api.builder.Message;
import org.kie.api.builder.model.KieBaseModel;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.conf.ClockTypeOption;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.internal.utils.KieHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration;
import org.springframework.stereotype.Component;

import org.drools.template.ObjectDataCompiler;


import java.io.*;
import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.StringReader;

@Component
public class KieSessionInitializer implements ApplicationRunner {

    @Autowired
    private KieSession kieSession;

    @Autowired
    private final FilmRepository filmRepository;

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private ProjectInfoAutoConfiguration projectInfoAutoConfiguration;

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
            if(f.getAward().isEmpty()) continue;
            BWItem bwItem = new BWItem(f.getName(), f.getAward());
            kieSession.insert(bwItem);
            BWItem bwItem2 = new BWItem(f.getMainActor().getName() + " " + f.getMainActor().getSurname(), f.getName());
            kieSession.insert(bwItem2);
        }

        // Get all users
        for (User u : userRepository.findAll()) {
            kieSession.insert(u);
        }

    }


}
