package com.ftn.sbnz.service.Entities.Models;

import lombok.*;
import org.kie.api.definition.type.Position;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BWItem {
    @Position(0)
    private String left;
    @Position(1)
    private String right;


}
