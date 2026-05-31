package com.jkpbmz.technologiebackendoweprojekt.projections.employee;

import com.jkpbmz.technologiebackendoweprojekt.projections.user.UserSaveRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmployeeSaveRequest {
    @NotBlank(message = "firstName is required")
    @Size(max = 50, message = "firstName must be at most 50 characters long")
    private String firstName;

    @NotBlank(message = "lastName is required")
    @Size(max = 50, message = "lastName must be at most 50 characters long")
    private String lastName;

    @NotBlank(message = "phoneNumber is required")
    @Size(max = 12, message = "phone number must be at most 12 characters long")
    private String phoneNumber;

    @NotNull(message = "position is required")
    @Positive(message = "position id must be positive")
    private Long position;

    @Valid
    private UserSaveRequest user;

    @Positive(message = "version must be positive")
    private Long version;
}
