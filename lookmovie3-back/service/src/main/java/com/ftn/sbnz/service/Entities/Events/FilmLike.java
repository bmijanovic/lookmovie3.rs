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
@Table(name = "FILM_LIKE")
@Role(Role.Type.EVENT)
@Timestamp("timestamp")
public class FilmLike {
    @Id
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @Column(nullable = false)
    private Boolean isLike;

    @ManyToOne(fetch = FetchType.LAZY)
    private Film film;

    @Column(nullable = false)
    private Date timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private Boolean isDone = false;
    private Boolean isDoneCep = false;
}
