package com.jkpbmz.technologiebackendoweprojekt.entities;

import com.jkpbmz.technologiebackendoweprojekt.enums.SizeEnum;
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
@Table(name = "loads")
public class Load {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "identifier")
    private String identifier;

    @Column(name = "type")
    private String type;

    @Column(name = "delivery_time")
    private String deliveryTime;

    @Column(name = "size")
    @Enumerated(EnumType.STRING)
    @ColumnTransformer(write = "?::SIZES")
    private SizeEnum size;

    @Column(name = "weight")
    private Float weight;

    @Column(name = "send_date", columnDefinition = "TIMESTAMP WITH TIMEZONE")
    private ZonedDateTime sendDate;

    @Column(name = "delivery_date", columnDefinition = "TIMESTAMP WITH TIMEZONE")
    private ZonedDateTime deliveryDate;

    @Column(name = "worth")
    private Double worth;
}
