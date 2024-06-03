package com.ftn.sbnz.service.Services;

import com.ftn.sbnz.service.Entities.Models.Film;
import com.ftn.sbnz.service.Entities.Tools.PageParams;
import com.ftn.sbnz.service.Entities.Tools.Paginated;
import com.ftn.sbnz.service.Repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class FilmService {
    @Autowired
    private FilmRepository filmRepository;

    public Paginated<Film> getAllPaginated(PageParams pageParams) {
        Pageable pageable = (Pageable) PageRequest.of(pageParams.getPage()-1, pageParams.getPageSize());
        Page<Film> films = filmRepository.findAllByNameStartingWith(pageParams.getSearch(), pageable);
        List<Film> filmsModel = films.getContent();

        return new Paginated<>(filmsModel, pageParams.getPage(), (int) films.getTotalElements(), films.getTotalPages());

    }
}
