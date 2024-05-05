package com.ftn.sbnz.service.Entities.Events;


import com.ftn.sbnz.service.Entities.Models.Film;
import com.ftn.sbnz.service.Entities.Models.User;
import jakarta.persistence.*;
import lombok.*;
import org.kie.api.definition.type.Timestamp;
import org.kie.api.definition.type.Role;

import java.util.Date;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "FILM_WATCH")
@Role(Role.Type.EVENT)
@Timestamp("timestamp")
public class FilmWatch {
    @Id
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Film film;

    @Column(nullable = false)
    private Date timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
