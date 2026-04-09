package com.jkpbmz.technologiebackendoweprojekt.projections.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jkpbmz.technologiebackendoweprojekt.enums.RoleEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserSaveRequest {
    private Long id;
    private String email;
    private String password;
    private List<RoleEnum> roles;

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
