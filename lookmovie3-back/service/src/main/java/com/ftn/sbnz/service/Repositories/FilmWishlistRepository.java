package com.ftn.sbnz.service.Repositories;

import com.ftn.sbnz.service.Entities.Events.FilmWishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FilmWishlistRepository  extends JpaRepository<FilmWishlist, UUID> {
}
