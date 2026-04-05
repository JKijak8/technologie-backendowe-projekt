package com.jkpbmz.technologiebackendoweprojekt.projections;

import lombok.Data;

import java.util.List;

@Data
public class EmployeeDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private PositionDTO position;
    private List<CourseSummaryDTO> courses;
    private UserSummaryDTO user;
}
