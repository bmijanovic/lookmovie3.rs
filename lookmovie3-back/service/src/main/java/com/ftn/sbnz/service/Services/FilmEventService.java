package com.ftn.sbnz.service.Services;

import com.ftn.sbnz.service.Entities.Events.FilmRating;
import com.ftn.sbnz.service.Entities.Events.FilmReview;
import com.ftn.sbnz.service.Entities.Models.Film;
import com.ftn.sbnz.service.Entities.Models.FilmGenre;
import com.ftn.sbnz.service.Entities.Models.User;
import com.ftn.sbnz.service.Repositories.FilmRatingRepository;
import com.ftn.sbnz.service.Repositories.FilmRepository;
import com.ftn.sbnz.service.Repositories.FilmReviewRepository;
import com.ftn.sbnz.service.Repositories.UserRepository;
import org.drools.core.common.DefaultFactHandle;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class FilmEventService {

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private FilmRatingRepository filmRatingRepository;

    @Autowired
    private FilmReviewRepository filmReviewRepository;

    @Autowired
    private KieContainer kieContainer;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KieSession kieSession;

    public Film leaveReview(User u, UUID filmId, String review, Boolean isPositive) {
        Film film = filmRepository.findById(filmId).orElseThrow(() -> new IllegalArgumentException("Film not found"));
        User user = userRepository.findById(u.getId()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        FilmReview filmReview = new FilmReview();
        filmReview.setId(UUID.randomUUID());
        filmReview.setReview(review);
        filmReview.setIsPositive(isPositive);
        filmReview.setUser(user);
        filmReview.setFilm(film);
        filmReview.setTimestamp(Date.from(Instant.now()));
        filmReviewRepository.save(filmReview);

        kieSession.setGlobal("likedFilm", film);
        kieSession.setGlobal("userId", user.getId());
        kieSession.insert(filmReview);

        kieSession.getAgenda().getAgendaGroup("review").setFocus();
        int fired = kieSession.fireAllRules();
        System.out.println("Fired " + fired + " rules");

        QueryResults results = kieSession.getQueryResults("getUserById", user.getId());
        for (QueryResultsRow row : results) {
            user = (User) row.get("$user");
        }
        System.out.println(user);

        user.update(user);
        userRepository.save(user);

        Film recommendedFilm = null;
        QueryResults results2 = kieSession.getQueryResults("getLastUserRecommendation", user.getId());
        for (QueryResultsRow row : results2) {
            recommendedFilm = (Film) row.get("$film");
            System.out.println("Recommended film: " + recommendedFilm.getName());
        }
        Film filmFromRepo = filmRepository.findById(recommendedFilm.getId()).orElseThrow(() -> new IllegalArgumentException("Film not found"));


        return filmFromRepo;

    }

    public Film rateFilm(User u, UUID filmId, Double rating) {
        Film film = filmRepository.findById(filmId).orElseThrow(() -> new IllegalArgumentException("Film not found"));
        User user = userRepository.findById(u.getId()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        FilmRating filmRating = new FilmRating();
        filmRating.setId(UUID.randomUUID());
        filmRating.setRating(rating);
        filmRating.setUser(user);
        filmRating.setFilm(film);
        filmRating.setTimestamp(Date.from(Instant.now()));
        filmRatingRepository.save(filmRating);

        kieSession.setGlobal("likedFilm", film);
        kieSession.setGlobal("userId", user.getId());
        kieSession.insert(filmRating);

        kieSession.getAgenda().getAgendaGroup("rating").setFocus();
        int fired = kieSession.fireAllRules();
        System.out.println("Fired " + fired + " rules");

        QueryResults results = kieSession.getQueryResults("getUserById", user.getId());
        for (QueryResultsRow row : results) {
            user = (User) row.get("$user");
        }
        System.out.println(user);

        user.update(user);
        userRepository.save(user);

        Film recommendedFilm = null;
        QueryResults results2 = kieSession.getQueryResults("getLastUserRecommendation", user.getId());
        for (QueryResultsRow row : results2) {
            recommendedFilm = (Film) row.get("$film");
            System.out.println("Recommended film: " + recommendedFilm.getName());
        }
        Film filmFromRepo = filmRepository.findById(recommendedFilm.getId()).orElseThrow(() -> new IllegalArgumentException("Film not found"));


        kieSession.setGlobal("genre", film.getGenre());
        kieSession.setGlobal("userId", user.getId());

        //print all objects in ksession
        for (FactHandle factHandle : kieSession.getFactHandles()) {
            System.out.println(kieSession.getObject(factHandle));
        }
        kieSession.getAgenda().getAgendaGroup("cep_genre").setFocus();
        fired = kieSession.fireAllRules();
        System.out.println("Fired " + fired + " rules");
        return filmFromRepo;
    }
}
