package com.jkpbmz.technologiebackendoweprojekt.mappers;

import com.jkpbmz.technologiebackendoweprojekt.entities.Load;
import com.jkpbmz.technologiebackendoweprojekt.projections.LoadDTO;
import com.jkpbmz.technologiebackendoweprojekt.projections.LoadSaveRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ContractMapper.class, DeliveryStateMapper.class})
public interface LoadMapper {
    @Mapping(source = "course.id", target = "courseId")
    LoadDTO toLoadDTO(Load load);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "contract", ignore = true)
    @Mapping(target = "deliveryState", ignore = true)
    @Mapping(target = "course", ignore = true)
    @Mapping(target = "sendDate", ignore = true)
    @Mapping(target = "deliveryDate", ignore = true)
    @Mapping(target = "deliveryTime", ignore = true)
    Load toLoad(LoadSaveRequest request);
}
