package com.jkpbmz.technologiebackendoweprojekt.projections.delivery_state;

import com.jkpbmz.technologiebackendoweprojekt.enums.DeliveryStatesEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class DeliveryStateSaveRequest {
    @NotBlank(message = "location is required")
    private String location;

    @NotNull(message = "deliveryState is required")
    private DeliveryStatesEnum deliveryState;

    private String comment;

    @Positive(message = "version must be positive")
    private Long version;
}
