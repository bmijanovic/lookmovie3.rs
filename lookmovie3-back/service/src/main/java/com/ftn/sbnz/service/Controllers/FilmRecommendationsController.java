package com.ftn.sbnz.service.Controllers;

import com.ftn.sbnz.service.Entities.Models.Film;
import com.ftn.sbnz.service.Entities.Models.User;
import com.ftn.sbnz.service.Services.FilmRecommendationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/film-recommendations")
public class FilmRecommendationsController {
    @Autowired
    private FilmRecommendationsService filmRecommendationsService;

    @GetMapping("/recommend")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<List<Film>> getRecommendationsForUser(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        List<Film> recommendedFilms = filmRecommendationsService.getRecommendationsForUser(user);
        return ResponseEntity.ok(recommendedFilms);
    }

    @GetMapping("/global-recommend")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<LinkedList<Film>> getGlobalRecommendations() {
        LinkedList<Film> recommendedFilms = filmRecommendationsService.getGlobalRecommendations();
        return ResponseEntity.ok(recommendedFilms);
    }
}
