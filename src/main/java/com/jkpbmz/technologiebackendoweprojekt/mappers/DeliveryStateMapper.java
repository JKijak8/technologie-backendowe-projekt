package com.jkpbmz.technologiebackendoweprojekt.mappers;

import com.jkpbmz.technologiebackendoweprojekt.entities.DeliveryState;
import com.jkpbmz.technologiebackendoweprojekt.projections.DeliveryStateDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeliveryStateMapper {
    DeliveryStateDTO toDeliveryStateDTO(DeliveryState deliveryState);
}
