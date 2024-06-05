package com.ftn.sbnz.service.Controllers;

import com.ftn.sbnz.service.Controllers.DTOs.CreateFilmDTO;
import com.ftn.sbnz.service.Controllers.DTOs.FilmRatingDTO;
import com.ftn.sbnz.service.Entities.Models.Actor;
import com.ftn.sbnz.service.Entities.Models.Director;
import com.ftn.sbnz.service.Entities.Models.Film;
import com.ftn.sbnz.service.Entities.Models.User;
import com.ftn.sbnz.service.Entities.Tools.PageParams;
import com.ftn.sbnz.service.Entities.Tools.Paginated;
import com.ftn.sbnz.service.Services.FilmService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/films")
public class FilmController {
    @Autowired
    private FilmService filmService;

    @GetMapping("/")
    @PreAuthorize("hasAuthority('ROLE_USER')")

    public ResponseEntity<Paginated<Film>> getAll(PageParams pageParams) {

        Paginated<Film> films = filmService.getAllPaginated(pageParams);

        return ResponseEntity.ok(films);
    }

    @GetMapping("/get-actors")
    public ResponseEntity<List<Actor>> getActors() {

        return ResponseEntity.ok(filmService.getActors());
    }

    @GetMapping("/get-directors")
    public ResponseEntity<List<Director>> getDirectors() {

        return ResponseEntity.ok(filmService.getDirectors());
    }

    @GetMapping("/main-actor-info/{actor}")
    public ResponseEntity<HashMap<String, Integer>> getMainActorInfo(@PathVariable String actor) {
        return ResponseEntity.ok(filmService.getMainActorInfo(actor));
    }
    @PostMapping("/create-film")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Film> createFilm(@RequestBody CreateFilmDTO film) {
        return ResponseEntity.ok(filmService.createFilm(film));
    }

}
