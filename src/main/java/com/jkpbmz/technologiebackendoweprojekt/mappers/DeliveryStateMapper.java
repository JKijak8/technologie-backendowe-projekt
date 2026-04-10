package com.jkpbmz.technologiebackendoweprojekt.mappers;

import com.jkpbmz.technologiebackendoweprojekt.entities.DeliveryState;
import com.jkpbmz.technologiebackendoweprojekt.projections.delivery_state.DeliveryStateDTO;
import com.jkpbmz.technologiebackendoweprojekt.projections.delivery_state.DeliveryStateSaveRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DeliveryStateMapper {
    DeliveryStateDTO toDeliveryStateDTO(DeliveryState deliveryState);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastUpdated", expression = "java(java.time.ZonedDateTime.now())")
    @Mapping(target = "loads", ignore = true)
    DeliveryState toDeliveryState(DeliveryStateSaveRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastUpdated", expression = "java(java.time.ZonedDateTime.now())")
    @Mapping(target = "loads", ignore = true)
    void updateDeliveryState(DeliveryStateSaveRequest request,@MappingTarget DeliveryState deliveryState);
}
