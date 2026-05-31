package com.jkpbmz.technologiebackendoweprojekt.projections.delivery_state;

import com.jkpbmz.technologiebackendoweprojekt.enums.DeliveryStatesEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DeliveryStateSaveRequest {
    @NotBlank(message = "location is required")
    @Size(max = 255, message = "location must be at most 255 characters long")
    private String location;

    @NotNull(message = "deliveryState is required")
    private DeliveryStatesEnum deliveryState;

    private String comment;

    @Positive(message = "version must be positive")
    private Long version;
}
