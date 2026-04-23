package com.jkpbmz.technologiebackendoweprojekt.mappers;

import com.jkpbmz.technologiebackendoweprojekt.entities.User;
import com.jkpbmz.technologiebackendoweprojekt.projections.user.UserRegisterRequest;
import com.jkpbmz.technologiebackendoweprojekt.projections.user.UserSaveRequest;
import com.jkpbmz.technologiebackendoweprojekt.projections.user.UserSummaryDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserSummaryDTO toUserSummaryDTO(User user);

    @Mapping(target = "createdAt", expression = "java(java.time.ZonedDateTime.now())")
    User toUser(UserSaveRequest user);

    @Mapping(target = "createdAt", expression = "java(java.time.ZonedDateTime.now())")
    @Mapping(target = "roles", expression = "java(List.of(RoleEnum.NONE))")
    User toUser(UserRegisterRequest user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUser(UserSaveRequest request, @MappingTarget User user);
}
