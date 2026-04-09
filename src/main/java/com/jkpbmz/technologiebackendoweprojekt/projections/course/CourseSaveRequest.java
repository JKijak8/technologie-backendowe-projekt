package com.jkpbmz.technologiebackendoweprojekt.projections.course;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class CourseSaveRequest {
    private String destination;
    private ZonedDateTime date;
    private String description;
    private Double cost;
    private Long driver;
}
