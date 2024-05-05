package com.ftn.sbnz.service.Controllers;

import com.ftn.sbnz.service.Controllers.DTOs.FilmRatingDTO;
import com.ftn.sbnz.service.Entities.Models.User;
import com.ftn.sbnz.service.Services.FilmEventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/film-events")
public class FilmEventsController {

    @Autowired
    private FilmEventService filmEventService;

    @PostMapping("/rating")
    @Valid
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<String> rateFilm(Authentication authentication, @Valid @RequestBody FilmRatingDTO dto) {
        User user = (User) authentication.getPrincipal();
        if (user == null) {
            return ResponseEntity.badRequest().body("User not authenticated");
        }
        filmEventService.rateFilm(user, dto.getFilmId(), dto.getRating());
        return ResponseEntity.ok("Film rated");
    }
}
