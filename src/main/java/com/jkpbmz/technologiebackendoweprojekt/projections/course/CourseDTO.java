package com.jkpbmz.technologiebackendoweprojekt.projections.course;

import com.jkpbmz.technologiebackendoweprojekt.projections.employee.DriverSummaryDTO;
import com.jkpbmz.technologiebackendoweprojekt.projections.load.LoadSummaryDTO;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@Data
public class CourseDTO {
    private Long id;
    private String destination;
    private ZonedDateTime date;
    private String description;
    private Double cost;
    private List<LoadSummaryDTO> loads;
    private DriverSummaryDTO driver;
}
