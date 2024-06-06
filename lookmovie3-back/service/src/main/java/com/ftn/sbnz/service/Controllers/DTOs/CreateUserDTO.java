package com.ftn.sbnz.service.Controllers.DTOs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateUserDTO {
    private String name;
    private String email;
    private String password;
}
