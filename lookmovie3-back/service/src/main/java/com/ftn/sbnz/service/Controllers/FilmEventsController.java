package com.ftn.sbnz.service.Controllers;

import com.ftn.sbnz.service.Controllers.DTOs.FilmRatingDTO;
import com.ftn.sbnz.service.Controllers.DTOs.FilmReviewDTO;
import com.ftn.sbnz.service.Controllers.DTOs.FilmWatchedDTO;
import com.ftn.sbnz.service.Controllers.DTOs.FilmWishlistDTO;
import com.ftn.sbnz.service.Entities.Events.FilmReview;
import com.ftn.sbnz.service.Entities.Models.Film;
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
    public ResponseEntity<Film> rateFilm(Authentication authentication, @Valid @RequestBody FilmRatingDTO dto) {
        User user = (User) authentication.getPrincipal();
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        Film recommendedFilm = filmEventService.rateFilm(user, dto.getFilmId(), dto.getRating());
        return ResponseEntity.ok(recommendedFilm);
    }

    @PostMapping("/wishlist")
    @Valid
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Film> wishlist(Authentication authentication, @Valid @RequestBody FilmWishlistDTO dto) {
        User user = (User) authentication.getPrincipal();
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        Film recommendedFilm = filmEventService.wishlisted(user, dto.getFilmId());
        return ResponseEntity.ok(recommendedFilm);
    }
    @PostMapping("/watched")
    @Valid
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Film> watchedTillTheEnd(Authentication authentication, @Valid @RequestBody FilmWatchedDTO dto) {
        User user = (User) authentication.getPrincipal();
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        Film recommendedFilm = filmEventService.filmWathced(user, dto.getFilmId(), dto.getDuration());
        return ResponseEntity.ok(recommendedFilm);
    }

    @PostMapping("/review")
    @Valid
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Film> leaveReview(Authentication authentication, @Valid @RequestBody FilmReviewDTO dto) {
        User user = (User) authentication.getPrincipal();
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        Film recommendedFilm = filmEventService.leaveReview(user, dto.getFilmId(), dto.getReview(), dto.getIsPositive());
        return ResponseEntity.ok(recommendedFilm);
    }
}
