package com.jkpbmz.technologiebackendoweprojekt.services;

import com.jkpbmz.technologiebackendoweprojekt.enums.RoleEnum;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;

import javax.crypto.SecretKey;
import java.util.Date;

@AllArgsConstructor
public class Jwt {
    private final Claims claims;
    private final SecretKey secretKey;

    public boolean isExpired() {
        return claims.getExpiration().before(new Date());
    }

    public Date getExpiration() {
        return claims.getExpiration();
    }

    public Long getId() {
        return Long.valueOf(claims.getSubject());
    }

    public RoleEnum getRole() {
        return RoleEnum.valueOf(claims.get("role", String.class));
    }

    public String toString() {
        return Jwts.builder().claims(claims).signWith(secretKey).compact();
    }
}
