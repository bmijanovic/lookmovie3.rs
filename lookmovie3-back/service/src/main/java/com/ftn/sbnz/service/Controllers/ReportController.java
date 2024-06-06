package com.ftn.sbnz.service.Controllers;

import com.ftn.sbnz.service.Entities.Models.User;
import com.ftn.sbnz.service.Services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping("/users-favourite-genres")
    public ResponseEntity<HashMap<String, Integer>> usersFavouriteGenres(Authentication authentication){
        User user = (User) authentication.getPrincipal();
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(reportService.usersFavouriteGenres(user));
    }

    @GetMapping("/users-favourite-films")
    public ResponseEntity<HashMap<String, Integer>> usersFavouriteFilms(Authentication authentication){
        User user = (User) authentication.getPrincipal();
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(reportService.usersFavouriteFilms(user));
    }

    @GetMapping("/users-favourite-directors")
    public ResponseEntity<HashMap<String, Integer>> usersFavouriteDirectors(Authentication authentication){
        User user = (User) authentication.getPrincipal();
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(reportService.usersFavouriteDirectors(user));
    }

    @GetMapping("/users-favourite-actors")
    public ResponseEntity<HashMap<String, Integer>> usersFavouriteActors(Authentication authentication){
        User user = (User) authentication.getPrincipal();
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(reportService.usersFavouriteActors(user));
    }

    @GetMapping("/users-favourite-watchtime")
    public ResponseEntity<HashMap<Integer, Integer>> usersFavouriteWatchtime(Authentication authentication){
        User user = (User) authentication.getPrincipal();
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(reportService.usersFavouriteWatchtime(user));
    }

    @GetMapping("/users-favourite-film-rating")
    public ResponseEntity<HashMap<String, Double>> usersFavouriteFilmRating(Authentication authentication){
        User user = (User) authentication.getPrincipal();
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(reportService.usersFavouriteFilmsRating(user));
    }
}
