package com.ftn.sbnz.service.Repositories;

import com.ftn.sbnz.service.Entities.Models.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ActorRepository extends JpaRepository<Actor, UUID> {
}
