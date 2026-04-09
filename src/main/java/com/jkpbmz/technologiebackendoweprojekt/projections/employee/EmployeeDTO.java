package com.jkpbmz.technologiebackendoweprojekt.projections.employee;

import com.jkpbmz.technologiebackendoweprojekt.projections.position.PositionDTO;
import com.jkpbmz.technologiebackendoweprojekt.projections.user.UserSummaryDTO;
import com.jkpbmz.technologiebackendoweprojekt.projections.course.CourseSummaryDTO;
import lombok.Data;

import java.util.List;

@Data
public class EmployeeDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private PositionDTO position;
    private List<CourseSummaryDTO> courses;
    private UserSummaryDTO user;
}
