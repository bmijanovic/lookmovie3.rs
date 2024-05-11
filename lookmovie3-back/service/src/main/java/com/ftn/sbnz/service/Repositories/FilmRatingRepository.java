package com.ftn.sbnz.service.Repositories;

import com.ftn.sbnz.service.Entities.Events.FilmRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FilmRatingRepository extends JpaRepository<FilmRating, UUID> {
}
