package com.ftn.sbnz.service.Entities.Models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Genre {
    private String genre;
    private Boolean isDone = false;

    public Genre(String genre) {
        this.genre = genre;
        this.isDone = false;
    }
}
