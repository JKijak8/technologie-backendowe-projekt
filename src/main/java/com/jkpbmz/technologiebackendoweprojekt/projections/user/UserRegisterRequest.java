package com.jkpbmz.technologiebackendoweprojekt.projections.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegisterRequest {
    @NotBlank(message = "email is required")
    @Email(message = "invalid email")
    @Size(max = 255, message = "email must be at most 255 characters")
    private String email;

    @NotBlank(message = "password is required")
    @Size(min = 8, max = 255, message = "password must be at least 8 characters, and at most 255")
    private String password;
}
