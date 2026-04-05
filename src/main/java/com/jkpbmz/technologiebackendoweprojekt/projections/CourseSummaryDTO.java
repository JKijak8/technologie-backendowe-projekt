package com.jkpbmz.technologiebackendoweprojekt.projections;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class CourseSummaryDTO {
    private Long id;
    private String destination;
    private ZonedDateTime date;
}
