package com.ftn.sbnz.service.Services;

import com.ftn.sbnz.service.Controllers.DTOs.CreateFilmDTO;
import com.ftn.sbnz.service.Entities.Models.*;
import com.ftn.sbnz.service.Entities.Tools.PageParams;
import com.ftn.sbnz.service.Entities.Tools.Paginated;
import com.ftn.sbnz.service.Repositories.ActorRepository;
import com.ftn.sbnz.service.Repositories.DirectorRepository;
import com.ftn.sbnz.service.Repositories.FilmRepository;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service

public class FilmService {


    @Autowired
    private FilmRepository filmRepository;
    @Qualifier("kieSession")
    @Autowired
    private KieSession kieSession;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private DirectorRepository directorRepository;

    public Paginated<Film> getAllPaginated(PageParams pageParams) {
        Pageable pageable = (Pageable) PageRequest.of(pageParams.getPage()-1, pageParams.getPageSize());
        Page<Film> films = filmRepository.findAllByNameStartingWith(pageParams.getSearch(), pageable);
        List<Film> filmsModel = films.getContent();

        return new Paginated<>(filmsModel, pageParams.getPage(), (int) films.getTotalElements(), films.getTotalPages());

    }

    public HashMap<String, Integer> getMainActorInfo(String actor) {
        kieSession.insert(new BWUser(actor));
        int fired = kieSession.fireAllRules();
        System.out.println("Fired: " + fired);
        HashMap<String, Integer> map = new HashMap<>();
        QueryResults results = kieSession.getQueryResults("getAwards");
        for (QueryResultsRow row : results) {
            map = (HashMap<String, Integer>) row.get("$awards");
        }
        if(map == null) {
            map = new HashMap<String, Integer>();
        }
        if (!map.isEmpty()) {
            return map;
        }
        return null;
    }

    public List<Actor> getActors() {
        return actorRepository.findAll();

    }

    public List<Director> getDirectors() {
        return directorRepository.findAll();
    }

    public Film createFilm(CreateFilmDTO film) {
        Film newFilm = new Film();
        Actor actor = actorRepository.findById(film.getMainActorId()).orElseThrow(() -> new IllegalArgumentException("Actor not found"));
        Director director = directorRepository.findById(film.getDirectorId()).orElseThrow(() -> new IllegalArgumentException("Director not found"));
        newFilm.setMainActor(actor);
        newFilm.setDirector(director);
        newFilm.setAward(film.getAward().toUpperCase());
        newFilm.setDescription(film.getDescription());
        newFilm.setDuration(film.getDuration());
        newFilm.setGenre(film.getGenre());
        newFilm.setImage(film.getImage());
        newFilm.setName(film.getName());
        newFilm.setYear(film.getYear());
        newFilm.setId(UUID.randomUUID());

        kieSession.insert(newFilm);
        if(!newFilm.getAward().isEmpty()) {
            BWItem bwItem = new BWItem(newFilm.getName(), newFilm.getAward());
            kieSession.insert(bwItem);
            BWItem bwItem2 = new BWItem(newFilm.getMainActor().getName() + " " + newFilm.getMainActor().getSurname(), newFilm.getName());
            kieSession.insert(bwItem2);
        }
        return filmRepository.save(newFilm);



    }
}
