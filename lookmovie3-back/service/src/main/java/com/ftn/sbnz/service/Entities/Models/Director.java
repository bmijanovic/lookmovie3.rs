package com.ftn.sbnz.service.Entities.Models;

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
@Table(name = "DIRECTOR")
public class Director {
    @Id
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;
    private String Name;
    private String Surname;

    @OneToMany(mappedBy = "director", fetch = FetchType.LAZY)
    private List<Film> directedFilms;

}