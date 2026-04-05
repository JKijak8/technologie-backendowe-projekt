package com.jkpbmz.technologiebackendoweprojekt.entities;

import com.jkpbmz.technologiebackendoweprojekt.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

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

    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIMEZONE")
    private ZonedDateTime createdAt;

    @ElementCollection(targetClass = RoleEnum.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private List<RoleEnum> roles;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
