package com.jkpbmz.technologiebackendoweprojekt.projections.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jkpbmz.technologiebackendoweprojekt.enums.RoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserSaveRequest {
    @Positive(message = "user id must be positive")
    private Long id;

    @Email(message = "email is not valid")
    @Size(max = 255, message = "email must be at most 255 characters long")
    private String email;

    @Size(min = 8, max = 255, message = "password must be at least 8, and at most 255 characters long")
    private String password;

    private List<RoleEnum> roles;

    @Positive(message = "version must be positive")
    private Long version;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public UserSaveRequest(Long id) {
        this.id = id;
    }

    @JsonIgnore
    public boolean isIdOnly() {
        return id != null && !hasAdditionalData();
    }

    @JsonIgnore
    public boolean isDataOnly() {
        return id == null && hasAdditionalData();
    }

    @JsonIgnore
    private boolean hasAdditionalData() {
        return email != null || password != null || roles != null;
    }
}
