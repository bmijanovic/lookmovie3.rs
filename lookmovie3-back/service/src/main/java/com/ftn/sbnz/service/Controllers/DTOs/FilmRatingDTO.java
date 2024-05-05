package com.ftn.sbnz.service.Controllers.DTOs;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmRatingDTO {
    private UUID filmId;
    @Range(min = 1, max = 5, message = "Rating must be between 1 and 5")
    private Double rating;
}
