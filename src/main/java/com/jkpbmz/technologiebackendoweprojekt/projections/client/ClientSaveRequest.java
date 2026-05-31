package com.jkpbmz.technologiebackendoweprojekt.projections.client;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClientSaveRequest {
    @NotBlank(message = "name is required")
    @Size(max = 100, message = "name must ve at most 100 characters long")
    private String name;

    @NotBlank(message = "nip is required")
    @Size(min = 10, max = 10, message = "nip must be exactly 10 characters long")
    private String nip;

    @NotBlank(message = "phoneNumber is required")
    @Size(max = 12, message = "phone number must be at most 12 characters long")
    private String phoneNumber;

    @Email(message = "email is not valid")
    @Size(message = "email must be at most 255 characters long")
    private String email;

    @Positive(message = "version number must be positive")
    private Long version;
}
