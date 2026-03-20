package com.jkpbmz.technologiebackendoweprojekt.entities;

import com.jkpbmz.technologiebackendoweprojekt.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnTransformer;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    @ColumnTransformer(write = "?::ROLES")
    private RoleEnum role;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIMEZONE")
    private ZonedDateTime createdAt;
}
