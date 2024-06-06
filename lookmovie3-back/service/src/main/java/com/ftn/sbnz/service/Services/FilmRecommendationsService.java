package com.ftn.sbnz.service.Services;

import com.ftn.sbnz.service.Entities.Models.Film;
import com.ftn.sbnz.service.Entities.Models.User;
import com.ftn.sbnz.service.Repositories.UserRepository;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FilmRecommendationsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private KieSession kieSession;

    public List<Film> getRecommendationsForUser(User u) {
        User user = userRepository.findById(u.getId()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        ArrayList<Film> makeList = new ArrayList<>();
        QueryResults results = kieSession.getQueryResults("getMakeList", user.getId());


        for (QueryResultsRow row : results) {
            makeList = (ArrayList<Film>) row.get("$list");
        }
        if(makeList == null) {
            makeList = new ArrayList<>();
        }
        if (!makeList.isEmpty()) {
            return makeList;
        }


        return user.getRecommendedFilms();
    }

    public LinkedList<Film> getGlobalRecommendations() {
        LinkedList<Film> makeList = new LinkedList<>();
        QueryResults results = kieSession.getQueryResults("getGlobalList");

        for (QueryResultsRow row : results) {
            makeList = (LinkedList<Film>) row.get("$list");
        }
        if(makeList == null) {
            makeList = new LinkedList<>();
        }

        return makeList;
    }
}
