package com.ftn.sbnz.service.Services;

import com.ftn.sbnz.service.Entities.Models.User;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class ReportService {
    
    @Autowired
    private KieSession kieSession;

    public HashMap<String, Integer> usersFavouriteGenres(User user) {
        QueryResults results = kieSession.getQueryResults("usersFavouriteGenres", user.getId());
        HashMap<String, Integer> genresMap = new HashMap<>();
        for (QueryResultsRow row : results) {
            genresMap = (HashMap<String, Integer>) row.get("$map");
        }
        if(genresMap == null) {
            genresMap = new HashMap<String, Integer>();
        }
        if (!genresMap.isEmpty()) {
            return genresMap;
        }
        return null;
    }

    public HashMap<String, Integer> usersFavouriteFilms(User user) {
        QueryResults results = kieSession.getQueryResults("usersFavouriteFilms", user.getId());
        HashMap<String, Integer> genresMap = new HashMap<>();
        for (QueryResultsRow row : results) {
            genresMap = (HashMap<String, Integer>) row.get("$map");
        }
        if(genresMap == null) {
            genresMap = new HashMap<String, Integer>();
        }
        if (!genresMap.isEmpty()) {
            return genresMap;
        }
        return null;
    }

    public HashMap<String, Integer> usersFavouriteDirectors(User user) {
        QueryResults results = kieSession.getQueryResults("usersFavouriteDirectors", user.getId());
        HashMap<String, Integer> genresMap = new HashMap<>();
        for (QueryResultsRow row : results) {
            genresMap = (HashMap<String, Integer>) row.get("$map");
        }
        if(genresMap == null) {
            genresMap = new HashMap<String, Integer>();
        }
        if (!genresMap.isEmpty()) {
            return genresMap;
        }
        return null;
    }
    public HashMap<String, Integer> usersFavouriteActors(User user) {
        QueryResults results = kieSession.getQueryResults("usersFavouriteActors", user.getId());
        HashMap<String, Integer> genresMap = new HashMap<>();
        for (QueryResultsRow row : results) {
            genresMap = (HashMap<String, Integer>) row.get("$map");
        }
        if(genresMap == null) {
            genresMap = new HashMap<String, Integer>();
        }
        if (!genresMap.isEmpty()) {
            return genresMap;
        }
        return null;
    }



    public HashMap<Integer, Integer> usersFavouriteWatchtime(User user) {
        QueryResults results = kieSession.getQueryResults("usersFavouriteWatchtime", user.getId());
        HashMap<Integer, Integer> genresMap = new HashMap<>();
        for (QueryResultsRow row : results) {
            genresMap = (HashMap<Integer, Integer>) row.get("$map");
        }
        if(genresMap == null) {
            genresMap = new HashMap<Integer, Integer>();
        }
        if (!genresMap.isEmpty()) {
            return genresMap;
        }
        return null;
    }

    public HashMap<String, Double> usersFavouriteFilmsRating(User user) {
        QueryResults results = kieSession.getQueryResults("usersFavouriteFilmsRating", user.getId());
        HashMap<String, Double> genresMap = new HashMap<>();
        for (QueryResultsRow row : results) {
            genresMap = (HashMap<String, Double>) row.get("$map");
        }
        if(genresMap == null) {
            genresMap = new HashMap<String, Double>();
        }
        if (!genresMap.isEmpty()) {
            return genresMap;
        }
        return null;
    }
}
