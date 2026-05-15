package com.jkpbmz.technologiebackendoweprojekt.repositories;

import com.jkpbmz.technologiebackendoweprojekt.entities.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
    List<RefreshToken> getAllByExpirationBefore(ZonedDateTime expirationBefore);
}
