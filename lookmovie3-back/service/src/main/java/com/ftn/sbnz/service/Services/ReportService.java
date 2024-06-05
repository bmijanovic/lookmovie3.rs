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
}
