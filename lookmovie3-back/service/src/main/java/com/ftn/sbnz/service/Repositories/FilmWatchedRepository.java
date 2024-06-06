package com.ftn.sbnz.service.Repositories;

import com.ftn.sbnz.service.Entities.Events.FilmWatch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FilmWatchedRepository extends JpaRepository<FilmWatch, UUID> {
}
