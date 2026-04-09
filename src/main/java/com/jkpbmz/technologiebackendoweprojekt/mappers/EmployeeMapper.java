package com.jkpbmz.technologiebackendoweprojekt.mappers;

import com.jkpbmz.technologiebackendoweprojekt.entities.Course;
import com.jkpbmz.technologiebackendoweprojekt.entities.Employee;
import com.jkpbmz.technologiebackendoweprojekt.entities.Position;
import com.jkpbmz.technologiebackendoweprojekt.entities.User;
import com.jkpbmz.technologiebackendoweprojekt.exceptions.BadRequestException;
import com.jkpbmz.technologiebackendoweprojekt.exceptions.NotFoundException;
import com.jkpbmz.technologiebackendoweprojekt.projections.course.CourseSummaryDTO;
import com.jkpbmz.technologiebackendoweprojekt.projections.employee.DriverSummaryDTO;
import com.jkpbmz.technologiebackendoweprojekt.projections.employee.EmployeeDTO;
import com.jkpbmz.technologiebackendoweprojekt.projections.employee.EmployeeSaveRequest;
import com.jkpbmz.technologiebackendoweprojekt.projections.employee.EmployeeSummaryDTO;
import com.jkpbmz.technologiebackendoweprojekt.projections.position.PositionDTO;
import com.jkpbmz.technologiebackendoweprojekt.projections.user.UserSaveRequest;
import com.jkpbmz.technologiebackendoweprojekt.projections.user.UserSummaryDTO;
import com.jkpbmz.technologiebackendoweprojekt.repositories.UserRepository;
import com.jkpbmz.technologiebackendoweprojekt.services.PositionService;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.sql.Driver;
import java.util.List;
import java.util.Objects;
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

    DriverSummaryDTO toDriverSummaryDTO(Driver driver);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "position", target = "position", qualifiedByName = "getPosition")
    @Mapping(source = "user", target = "user", qualifiedByName = "getUser")
    Employee toEmployee(EmployeeSaveRequest request,
                        @Context PositionService positionService,
                        @Context UserRepository userRepository);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "position", target = "position", qualifiedByName = "getPosition")
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "courses", ignore = true)
    void updateEmployee(EmployeeSaveRequest request,
                        @MappingTarget Employee employee,
                        @Context PositionService positionService,
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
    static Position getPosition(Long id, @Context PositionService positionService) {
        return positionService.getPosition(id);
    }

    @Named("getUser")
    static User getUser(UserSaveRequest request, @Context UserRepository userRepository) {
        if (request == null) return null;
        else if (request.isIdOnly()) return userRepository.findById(request.getId()).orElse(null);
        else if (request.isDataOnly()) return USER_MAPPER.toUser(request);
        else throw new BadRequestException("Payload should contain either User ID or only User data.");
    }

    @AfterMapping
    static void updateUser(EmployeeSaveRequest employeeSaveRequest,
                           @MappingTarget Employee employee,
                           @Context UserRepository userRepository) {
        Long currentUserId = (employee.getUser() != null) ? employee.getUser().getId() : null;

        UserSaveRequest userSaveRequest = employeeSaveRequest.getUser();

        if (userSaveRequest == null) employee.setUser(null);
        else if (userSaveRequest.isIdOnly()) {
            if (!Objects.equals(userSaveRequest.getId(), currentUserId)) employee.setUser(
                    userRepository.findById(userSaveRequest.getId()).orElse(null)
            );
        }
        else if (userSaveRequest.isDataOnly()) employee.setUser(USER_MAPPER.toUser(userSaveRequest));
        else if (Objects.equals(userSaveRequest.getId(), currentUserId)) USER_MAPPER.updateUser(userSaveRequest, employee.getUser());
        else throw new BadRequestException("Payload should contain either User ID, only User data to create a new User, or already assigned ID and new data to update.");
    }

    @Named("positionToString")
    static String positionToString(Position position) {
        return position.getPosition();
    }
}
