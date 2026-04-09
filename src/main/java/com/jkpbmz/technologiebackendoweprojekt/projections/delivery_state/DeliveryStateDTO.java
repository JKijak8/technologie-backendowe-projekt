package com.jkpbmz.technologiebackendoweprojekt.projections.delivery_state;

import com.jkpbmz.technologiebackendoweprojekt.enums.DeliveryStatesEnum;
import lombok.Data;
import java.time.ZonedDateTime;

@Data
public class DeliveryStateDTO {
    private Long id;
    private String location;
    private DeliveryStatesEnum deliveryState;
    private String comment;
    private ZonedDateTime lastUpdated;
}
