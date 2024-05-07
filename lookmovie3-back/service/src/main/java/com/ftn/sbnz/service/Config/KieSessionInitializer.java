package com.ftn.sbnz.service.Config;

import com.ftn.sbnz.service.Entities.Models.Film;
import com.ftn.sbnz.service.Entities.Models.User;
import com.ftn.sbnz.service.Repositories.FilmRepository;
import com.ftn.sbnz.service.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class KieSessionInitializer implements ApplicationRunner {

    @Autowired
    private final KieSession kieSession;

    @Autowired
    private final FilmRepository filmRepository;

    @Autowired
    private final UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) {
		//get all films
        System.out.println("Initializing KieSession");
		for(Film f : filmRepository.findAll()){
			kieSession.insert(f);
		}

		//get all users
		for(User u : userRepository.findAll()){
			kieSession.insert(u);
		}
    }
}
