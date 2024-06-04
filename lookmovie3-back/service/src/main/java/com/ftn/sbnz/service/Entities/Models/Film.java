package com.ftn.sbnz.service.Entities.Models;

import com.ftn.sbnz.service.Entities.Events.FilmReview;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "FILM")
public class Film {
    @Id
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
//    @Enumerated(EnumType.STRING)
    private String genre;

    @Column(nullable = false)
    private Integer duration;

    @Column(nullable = false)
    private Integer year;

    @ManyToOne(fetch = FetchType.EAGER)
    private Actor mainActor;

    @ManyToOne(fetch = FetchType.EAGER)
    private Director director;

    @Column(nullable = false)
    private String description;

    private List<Awards> awards;

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genre=" + genre +
                ", duration=" + duration +
                ", year=" + year +
                ", mainActor=" + mainActor +
                ", director=" + director +
                ", description='" + description + '\'' +
                ", awards=" + awards +
                '}';
    }


}
