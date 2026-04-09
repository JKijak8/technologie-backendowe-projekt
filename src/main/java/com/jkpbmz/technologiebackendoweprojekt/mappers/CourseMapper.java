package com.jkpbmz.technologiebackendoweprojekt.mappers;

import com.jkpbmz.technologiebackendoweprojekt.entities.Course;
import com.jkpbmz.technologiebackendoweprojekt.entities.Load;
import com.jkpbmz.technologiebackendoweprojekt.projections.course.CourseDTO;
import com.jkpbmz.technologiebackendoweprojekt.projections.course.CourseSummaryDTO;
import com.jkpbmz.technologiebackendoweprojekt.projections.employee.DriverSummaryDTO;
import com.jkpbmz.technologiebackendoweprojekt.projections.load.LoadSummaryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.sql.Driver;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    LoadMapper LOAD_MAPPER = Mappers.getMapper(LoadMapper.class);
    EmployeeMapper EMPLOYEE_MAPPER = Mappers.getMapper(EmployeeMapper.class);

    CourseSummaryDTO toCourseSummaryDTO(Course course);

    @Mapping(source = "loads", target = "loads", qualifiedByName = "getLoadSummaries")
    CourseDTO toCourseDTO(Course course);

    @Named("getLoadSummaries")
    static List<LoadSummaryDTO> getLoadSummaries(List<Load> loads) {
        return loads.stream().map(LOAD_MAPPER::toLoadSummaryDTO).collect(Collectors.toList());
    }

    @Named("getDriverSummary")
    static DriverSummaryDTO getDriverSummary(Driver driver) {
        return EMPLOYEE_MAPPER.toDriverSummaryDTO(driver);
    }
}
