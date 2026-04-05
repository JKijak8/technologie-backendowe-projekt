package com.jkpbmz.technologiebackendoweprojekt.mappers;

import com.jkpbmz.technologiebackendoweprojekt.entities.Course;
import com.jkpbmz.technologiebackendoweprojekt.entities.Employee;
import com.jkpbmz.technologiebackendoweprojekt.entities.Position;
import com.jkpbmz.technologiebackendoweprojekt.entities.User;
import com.jkpbmz.technologiebackendoweprojekt.projections.CourseSummaryDTO;
import com.jkpbmz.technologiebackendoweprojekt.projections.EmployeeDTO;
import com.jkpbmz.technologiebackendoweprojekt.projections.PositionDTO;
import com.jkpbmz.technologiebackendoweprojekt.projections.UserSummaryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    PositionMapper POSITION_MAPPER = Mappers.getMapper(PositionMapper.class);
    CourseMapper COURSE_MAPPER = Mappers.getMapper(CourseMapper.class);
    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "position", target = "position", qualifiedByName = "getPositionDTO")
    @Mapping(source = "courses", target = "courses", qualifiedByName = "getCourseSummaries")
    @Mapping(source = "user", target = "user", qualifiedByName = "getUserSummary")
    EmployeeDTO toEmployeeDTO(Employee employee);

    @Named("getPositionDTO")
    static PositionDTO getPositionDTO(Position position) {
        return POSITION_MAPPER.toPositionDTO(position);
    }

    @Named("getCourseSummaries")
    static List<CourseSummaryDTO> getCourseSummaries(List<Course> courses) {
        return courses.stream().map(COURSE_MAPPER::toCourseSummaryDTO).collect(Collectors.toList());
    }

    @Named("getUserSummary")
    static UserSummaryDTO getUserSummary(User user) {
        return USER_MAPPER.toUserSummaryDTO(user);
    }
}
