package com.ftn.sbnz.service.Repositories;

import com.ftn.sbnz.service.Entities.Events.FilmReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FilmReviewRepository extends JpaRepository<FilmReview, UUID> {
}
