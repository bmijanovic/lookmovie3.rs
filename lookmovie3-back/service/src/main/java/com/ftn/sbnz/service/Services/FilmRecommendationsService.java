package com.ftn.sbnz.service.Services;

import com.ftn.sbnz.service.Entities.Models.Film;
import com.ftn.sbnz.service.Entities.Models.User;
import com.ftn.sbnz.service.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FilmRecommendationsService {
    @Autowired
    private UserRepository userRepository;

    public List<Film> getRecommendationsForUser(User u) {
        User user = userRepository.findById(u.getId()).orElseThrow(() -> new IllegalArgumentException("User not found"));



        return user.getRecommendedFilms();
    }
}
