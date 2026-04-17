package com.jkpbmz.technologiebackendoweprojekt.projections.auth;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
