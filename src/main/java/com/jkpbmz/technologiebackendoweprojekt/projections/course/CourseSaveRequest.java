package com.jkpbmz.technologiebackendoweprojekt.projections.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class CourseSaveRequest {
    @NotBlank(message = "destination is required")
    private String destination;

    @NotNull(message = "date is required")
    private ZonedDateTime date;

    private String description;

    @PositiveOrZero(message = "cost must be positive or zero")
    private Double cost;

    @Positive(message = "driver id must be positive")
    private Long driver;

    @Positive(message = "version number must be positive")
    private Long version;
}
