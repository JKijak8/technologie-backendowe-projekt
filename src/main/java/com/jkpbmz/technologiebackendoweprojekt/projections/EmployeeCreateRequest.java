package com.jkpbmz.technologiebackendoweprojekt.projections;

import lombok.Data;

@Data
public class EmployeeCreateRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Long position;
}
