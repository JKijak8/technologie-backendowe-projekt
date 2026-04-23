package com.jkpbmz.technologiebackendoweprojekt.projections.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class JwtResponse {
    private String token;
    private Date expiration;
}
