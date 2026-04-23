package com.jkpbmz.technologiebackendoweprojekt.projections.user;

import lombok.Data;

@Data
public class UserRegisterRequest {
    private String email;
    private String password;
}
