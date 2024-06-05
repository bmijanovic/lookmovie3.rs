package com.ftn.sbnz.service.Repositories;

import com.ftn.sbnz.service.Entities.Models.Director;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DirectorRepository extends JpaRepository<Director, UUID> {
}
