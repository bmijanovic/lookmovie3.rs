package com.ftn.sbnz.service;

import java.security.Security;
import java.util.Arrays;

import com.ftn.sbnz.service.Config.AppProperties;
import com.ftn.sbnz.service.Entities.Models.Film;
import com.ftn.sbnz.service.Entities.Models.User;
import com.ftn.sbnz.service.Repositories.FilmRepository;
import com.ftn.sbnz.service.Repositories.UserRepository;
import org.kie.api.runtime.KieSession;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableTransactionManagement
@EnableConfigurationProperties(AppProperties.class)
public class ServiceApplication  {

	private static KieContainer kieContainer;
	
	private static Logger log = LoggerFactory.getLogger(ServiceApplication.class);

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(ServiceApplication.class, args);
		KieSession kieSession = ctx.getBean(KieSession.class);
		//get all films
		FilmRepository filmRepository = ctx.getBean(FilmRepository.class);
		for(Film f : filmRepository.findAll()){
			kieSession.insert(f);
		}

		//get all users
		UserRepository userRepository = ctx.getBean(UserRepository.class);
		for(User u : userRepository.findAll()){
			kieSession.insert(u);
		}
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@PostConstruct
	public void init() {
		Security.addProvider(new BouncyCastleProvider());

	}


	@Bean
	public KieContainer kieContainer() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("com.ftn.sbnz", "kjar", "0.0.1-SNAPSHOT"));
		KieScanner kScanner = ks.newKieScanner(kContainer);
		kScanner.start(1000);
		kieContainer = kContainer;
		return kContainer;
	}

	@Bean
	public KieSession kieSession() {
		return kieContainer.newKieSession("ksession");
	}
	
	/*
	 * KieServices ks = KieServices.Factory.get(); KieContainer kContainer =
	 * ks.newKieContainer(ks.newReleaseId("drools-spring-v2",
	 * "drools-spring-v2-kjar", "0.0.1-SNAPSHOT")); KieScanner kScanner =
	 * ks.newKieScanner(kContainer); kScanner.start(10_000); KieSession kSession =
	 * kContainer.newKieSession();
	 */
}
