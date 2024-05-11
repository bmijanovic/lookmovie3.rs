package com.ftn.sbnz.service.Controllers.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmReviewDTO {
     private UUID filmId;
     private Boolean isPositive;
     private String review;
}
