package com.jkpbmz.technologiebackendoweprojekt.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "refresh_tokens")
public class RefreshToken {
    @Id
    @Column(name = "token")
    private String token;

    @Column(name = "expiration", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime expiration;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
