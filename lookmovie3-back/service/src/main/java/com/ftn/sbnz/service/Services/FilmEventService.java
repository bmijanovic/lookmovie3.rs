package com.ftn.sbnz.service.Services;

import com.ftn.sbnz.service.Entities.Events.FilmRating;
import com.ftn.sbnz.service.Entities.Models.Film;
import com.ftn.sbnz.service.Entities.Models.User;
import com.ftn.sbnz.service.Repositories.FilmRatingRepository;
import com.ftn.sbnz.service.Repositories.FilmRepository;
import com.ftn.sbnz.service.Repositories.UserRepository;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class FilmEventService {

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private FilmRatingRepository filmRatingRepository;

    @Autowired
    private KieContainer kieContainer;
    @Autowired
    private UserRepository userRepository;

    public void rateFilm(User user, UUID filmId, Double rating) {
        Film film = filmRepository.findById(filmId).orElseThrow(() -> new IllegalArgumentException("Film not found"));
        FilmRating filmRating = new FilmRating();
        filmRating.setId(UUID.randomUUID());
        filmRating.setRating(rating);
        filmRating.setUser(user);
        filmRating.setFilm(film);
        filmRating.setTimestamp(Date.from(Instant.now()));
        filmRatingRepository.save(filmRating);

        //TODO: add to kie session
        KieSession kieSession = kieContainer.newKieSession("ksession");
        kieSession.setGlobal("updatedUser", user); // Initialize the global variable
        kieSession.setGlobal("recommendedFilm", film);
        kieSession.setGlobal("likedFilm", film);
        kieSession.insert(user);
        kieSession.insert(film);
        kieSession.insert(filmRating);
        int fired = kieSession.fireAllRules();
        System.out.println("Fired " + fired + " rules");
        User updatedUser = (User) kieSession.getGlobal("updatedUser"); // Retrieve the updated object
        user.update(updatedUser);
        userRepository.save(user);


        Film recommendedFilm = (Film) kieSession.getGlobal("recommendedFilm");
        if (recommendedFilm != null) {
            System.out.println("Recommended film: " + recommendedFilm.getName());
        }



    }
}
