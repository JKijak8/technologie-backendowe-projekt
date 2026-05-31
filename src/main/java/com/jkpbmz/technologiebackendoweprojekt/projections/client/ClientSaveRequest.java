package com.jkpbmz.technologiebackendoweprojekt.projections.client;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ClientSaveRequest {
    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "nip is required")
    private String nip;

    @NotBlank(message = "phoneNumber is required")
    private String phoneNumber;

    @Email(message = "email is not valid")
    private String email;

    @Positive(message = "version number must be positive")
    private Long version;
}
