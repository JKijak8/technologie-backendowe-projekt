package com.jkpbmz.technologiebackendoweprojekt.scheduling_tasks;

import com.jkpbmz.technologiebackendoweprojekt.entities.RefreshToken;
import com.jkpbmz.technologiebackendoweprojekt.repositories.RefreshTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;

@AllArgsConstructor
@Component
public class ClearRefreshTokens {
    private static final int DAY_MILIS = 86400000;
    private final RefreshTokenRepository refreshTokenRepository;

    @Scheduled(fixedRate = DAY_MILIS)
    public void clearRefreshTokens() {
        List<RefreshToken> tokens = refreshTokenRepository.getAllByExpirationBefore(ZonedDateTime.now());
        refreshTokenRepository.deleteAll(tokens);
    }
}
