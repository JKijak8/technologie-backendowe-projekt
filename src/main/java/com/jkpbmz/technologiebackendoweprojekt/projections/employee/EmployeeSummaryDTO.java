package com.jkpbmz.technologiebackendoweprojekt.projections.employee;

import lombok.Data;

@Data
public class EmployeeSummaryDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String position;
}
