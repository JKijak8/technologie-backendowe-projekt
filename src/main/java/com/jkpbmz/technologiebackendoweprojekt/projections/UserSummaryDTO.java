package com.jkpbmz.technologiebackendoweprojekt.projections;

import com.jkpbmz.technologiebackendoweprojekt.enums.RoleEnum;
import lombok.Data;

import java.util.List;

@Data
public class UserSummaryDTO {
    private Long id;
    private String email;
    private List<RoleEnum> roles;
}
