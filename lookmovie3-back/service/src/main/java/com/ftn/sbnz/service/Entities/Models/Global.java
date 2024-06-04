package com.ftn.sbnz.service.Entities.Models;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Global {
    private UUID userId;
    private Film film;
}
