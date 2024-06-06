package com.ftn.sbnz.service.Controllers.DTOs;

import com.ftn.sbnz.service.Entities.Models.Actor;
import com.ftn.sbnz.service.Entities.Models.Director;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class CreateFilmDTO {

    private String name;

    private String genre;

    private Integer duration;

    private Integer year;

    private UUID mainActorId;

    private UUID directorId;

    private String description;

    private String image;

    private String award;


}
