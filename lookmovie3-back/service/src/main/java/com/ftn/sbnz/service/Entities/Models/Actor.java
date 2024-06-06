package com.ftn.sbnz.service.Entities.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Setter
@Table(name = "ACTOR")
public class Actor {
    @Id
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;
    private String Name;
    private String Surname;

    @OneToMany(mappedBy = "mainActor", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Film> mainActorFilms;

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", Surname='" + Surname + '\'' + '}';
    }
}