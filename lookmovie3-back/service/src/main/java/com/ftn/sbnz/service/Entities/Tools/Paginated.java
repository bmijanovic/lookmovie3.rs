package com.ftn.sbnz.service.Entities.Tools;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paginated<T> {
    private List<T> items;
    private int currentPage;
    private int totalItems;
    private int totalPages;
}