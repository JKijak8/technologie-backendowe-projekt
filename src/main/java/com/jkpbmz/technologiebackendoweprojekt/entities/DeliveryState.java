package com.jkpbmz.technologiebackendoweprojekt.entities;

import com.jkpbmz.technologiebackendoweprojekt.enums.DeliveryStatesEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnTransformer;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "delivery_states")
public class DeliveryState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "location")
    private String location;

    @Column(name = "delivery_state")
    @Enumerated(EnumType.STRING)
    @ColumnTransformer(write = "?::STATES")
    private DeliveryStatesEnum deliveryState;

    @Column(name = "comment")
    private String comment;

    @Column(name = "last_updated", columnDefinition = "TIMESTAMP WITH TIMEZONE")
    private ZonedDateTime lastUpdated;

    @OneToMany(mappedBy = "deliveryState")
    private List<Load> loads;
}
