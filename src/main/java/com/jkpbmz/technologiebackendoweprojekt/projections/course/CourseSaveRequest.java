package com.jkpbmz.technologiebackendoweprojekt.projections.course;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class CourseSaveRequest {
    @NotBlank(message = "destination is required")
    @Size(max = 255, message = "destination must be at most 255 characters long")
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
