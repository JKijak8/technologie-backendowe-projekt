package com.jkpbmz.technologiebackendoweprojekt.mappers;

import com.jkpbmz.technologiebackendoweprojekt.entities.User;
import com.jkpbmz.technologiebackendoweprojekt.projections.UserSaveRequest;
import com.jkpbmz.technologiebackendoweprojekt.projections.UserSummaryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserSummaryDTO toUserSummaryDTO(User user);

    @Mapping(target = "createdAt", expression = "java(java.time.ZonedDateTime.now())")
    User toUser(UserSaveRequest user);
}
