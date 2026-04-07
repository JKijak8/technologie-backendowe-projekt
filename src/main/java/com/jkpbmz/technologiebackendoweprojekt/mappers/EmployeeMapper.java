package com.jkpbmz.technologiebackendoweprojekt.mappers;

import com.jkpbmz.technologiebackendoweprojekt.entities.Course;
import com.jkpbmz.technologiebackendoweprojekt.entities.Employee;
import com.jkpbmz.technologiebackendoweprojekt.entities.Position;
import com.jkpbmz.technologiebackendoweprojekt.entities.User;
import com.jkpbmz.technologiebackendoweprojekt.exceptions.BadRequestException;
import com.jkpbmz.technologiebackendoweprojekt.exceptions.NotFoundException;
import com.jkpbmz.technologiebackendoweprojekt.projections.*;
import com.jkpbmz.technologiebackendoweprojekt.repositories.PositionRepository;
import com.jkpbmz.technologiebackendoweprojekt.repositories.UserRepository;
import org.mapstruct.*;
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

    @Mapping(source = "position", target = "position", qualifiedByName = "positionToString")
    EmployeeSummaryDTO toEmployeeSummaryDTO(Employee employee);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "position", target = "position", qualifiedByName = "getPosition")
    @Mapping(source = "user", target = "user", qualifiedByName = "getUser")
    Employee toEmployee(EmployeeSaveRequest request,
                        @Context PositionRepository positionRepository,
                        @Context UserRepository userRepository);

    @Named("getPositionDTO")
    static PositionDTO getPositionDTO(Position position) {
        return POSITION_MAPPER.toPositionDTO(position);
    }

    @Named("getCourseSummaries")
    static List<CourseSummaryDTO> getCourseSummaries(List<Course> courses) {
        if (courses == null) return null;
        return courses.stream().map(COURSE_MAPPER::toCourseSummaryDTO).collect(Collectors.toList());
    }

    @Named("getUserSummary")
    static UserSummaryDTO getUserSummary(User user) {
        return USER_MAPPER.toUserSummaryDTO(user);
    }

    @Named("getPosition")
    static Position getPosition(Long id, @Context PositionRepository positionRepository) {
        Position position = positionRepository.findById(id).orElse(null);
        if (position == null) {
            throw new NotFoundException("Position not found");
        }
        return position;
    }

    @Named("getUser")
    static User getUser(UserSaveRequest user, @Context UserRepository userRepository) {
        if (user == null) return null;
        else if (user.isIdOnly()) return userRepository.findById(user.getId()).orElse(null);
        else if (user.isDataOnly()) return USER_MAPPER.toUser(user);
        else throw new BadRequestException("Payload should contain either User ID or only User data.");
    }

    @Named("positionToString")
    static String positionToString(Position position) {
        return position.getPosition();
    }
}
