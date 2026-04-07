package com.jkpbmz.technologiebackendoweprojekt.projections;

import lombok.Data;

@Data
public class EmployeeSaveRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Long position;
    private UserSaveRequest user;
}
