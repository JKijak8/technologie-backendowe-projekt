package com.jkpbmz.technologiebackendoweprojekt.projections.employee;

import com.jkpbmz.technologiebackendoweprojekt.projections.user.UserSaveRequest;
import lombok.Data;

@Data
public class EmployeeSaveRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Long position;
    private UserSaveRequest user;
}
