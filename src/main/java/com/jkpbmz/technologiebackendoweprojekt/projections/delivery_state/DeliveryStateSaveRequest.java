package com.jkpbmz.technologiebackendoweprojekt.projections.delivery_state;

import com.jkpbmz.technologiebackendoweprojekt.enums.DeliveryStatesEnum;
import lombok.Data;

@Data
public class DeliveryStateSaveRequest {
    private String location;
    private DeliveryStatesEnum deliveryState;
    private String comment;
}
