package com.ftn.sbnz.service.Services;

import com.ftn.sbnz.service.Entities.Events.FilmRating;
import com.ftn.sbnz.service.Entities.Events.FilmReview;
import com.ftn.sbnz.service.Entities.Events.FilmWatch;
import com.ftn.sbnz.service.Entities.Events.FilmWishlist;
import com.ftn.sbnz.service.Entities.Models.Film;
import com.ftn.sbnz.service.Entities.Models.FilmGenre;
import com.ftn.sbnz.service.Entities.Models.Genre;
import com.ftn.sbnz.service.Entities.Models.User;
import com.ftn.sbnz.service.Repositories.*;
import org.drools.core.common.DefaultFactHandle;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;

import java.lang.reflect.Array;
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
    private FilmWatchedRepository filmWatchRepository;

    @Autowired
    private FilmWishlistRepository filmWishlistRepository;

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
        filmReview.setIsDone(false);
        filmReviewRepository.save(filmReview);

        kieSession.setGlobal("likedFilm", film);
        kieSession.setGlobal("userId", user.getId());
        kieSession.insert(filmReview);
        kieSession.insert(new Genre(film.getGenre()));

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
        if (recommendedFilm == null) {
            return null;
        }

        return filmRepository.findById(recommendedFilm.getId()).orElseThrow(() -> new IllegalArgumentException("Film not found"));

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
        kieSession.insert(new Genre(film.getGenre()));

        kieSession.insert(filmRating);

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
        if (recommendedFilm == null) {
            return null;
        }

        return filmRepository.findById(recommendedFilm.getId()).orElseThrow(() -> new IllegalArgumentException("Film not found"));
    }

    public Film wishlisted(User u, UUID filmId) {
        Film film = filmRepository.findById(filmId).orElseThrow(() -> new IllegalArgumentException("Film not found"));
        User user = userRepository.findById(u.getId()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        FilmWishlist filmWishlist = new FilmWishlist();
        filmWishlist.setId(UUID.randomUUID());
        filmWishlist.setUser(user);
        filmWishlist.setFilm(film);
        filmWishlist.setTimestamp(Date.from(Instant.now()));
        filmWishlistRepository.save(filmWishlist);

        kieSession.setGlobal("likedFilm", film);
        kieSession.setGlobal("userId", user.getId());
        kieSession.insert(filmWishlist);
        kieSession.insert(new Genre(film.getGenre()));

//      change focus
        kieSession.getAgenda().getAgendaGroup("wishlist").setFocus();
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
        if (recommendedFilm == null) {
            return null;
        }
        ArrayList<Film> films = new ArrayList<>();
        films.add(recommendedFilm);


        return filmRepository.findById(recommendedFilm.getId()).orElseThrow(() -> new IllegalArgumentException("Film not found"));
    }

    public Film filmWathced(User u, UUID filmId, Integer duration) {
        Film film = filmRepository.findById(filmId).orElseThrow(() -> new IllegalArgumentException("Film not found"));
        User user = userRepository.findById(u.getId()).orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (duration < film.getDuration()) {
            return null;
        }
        FilmWatch filmWatch = new FilmWatch();
        filmWatch.setId(UUID.randomUUID());
        filmWatch.setFilm(film);
        filmWatch.setUser(user);
        filmWatch.setTimestamp(Date.from(Instant.now()));

        filmWatchRepository.save(filmWatch);
        kieSession.setGlobal("userId", user.getId());
        kieSession.setGlobal("likedFilm", film);
        kieSession.insert(filmWatch);
        kieSession.insert(new Genre(film.getGenre()));


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
        if (recommendedFilm == null) {
            return null;
        }

        return filmRepository.findById(recommendedFilm.getId()).orElseThrow(() -> new IllegalArgumentException("Film not found"));





    }
}
