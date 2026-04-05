package com.jkpbmz.technologiebackendoweprojekt.entities;

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
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date", columnDefinition = "TIMESTAMP WITH TIMEZONE")
    private ZonedDateTime date;

    @Column(name = "description")
    private String description;

    @Column(name = "cost")
    private Double cost;

    @OneToMany(mappedBy = "course")
    private List<Load> loads;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Employee employee;
}
