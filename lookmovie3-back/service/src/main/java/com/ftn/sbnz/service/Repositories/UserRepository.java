package com.ftn.sbnz.service.Repositories;

import com.ftn.sbnz.service.Entities.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
	Optional<User> findByEmail(String email);

}