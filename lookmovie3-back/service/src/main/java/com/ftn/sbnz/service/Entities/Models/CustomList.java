package com.ftn.sbnz.service.Entities.Models;

import lombok.*;
import org.kie.api.definition.rule.All;

import java.util.LinkedList;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomList {
    private LinkedList<Film> films;
    private int size;
    public void addFirst(Film film) {
        if(contains(film)) {
            remove(film);
        }
        films.addFirst(film);
        if(films.size() > size) {
            films.removeLast();
        }
    }

    public void addLast(Film film) {
        if(contains(film)) {
            remove(film);
        }
        if(films.size() < size) {
            films.addLast(film);
        }
    }

    public Boolean contains(Film film) {
        return films.contains(film);
    }

    public void remove(Film film) {
        films.remove(film);
    }

    public void remove(int index) {
        films.remove(index);
    }

    public Film get(int index) {
        return films.get(index);
    }

    public int size() {
        return films.size();
    }

    public void clear() {
        films.clear();
    }

    public void addAll(List<Film> films) {
        this.films.addAll(films);
    }
}
