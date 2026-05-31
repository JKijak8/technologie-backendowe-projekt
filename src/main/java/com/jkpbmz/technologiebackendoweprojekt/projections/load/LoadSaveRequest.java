package com.jkpbmz.technologiebackendoweprojekt.projections.load;

import com.jkpbmz.technologiebackendoweprojekt.enums.SizeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoadSaveRequest {
    @NotBlank(message = "identifier is required")
    @Size(max = 255, message = "identifier must be at most 255 characters long")
    private String identifier;

    @NotBlank(message = "type is required")
    @Size(max = 255, message = "type must be at most 255 characters")
    private String type;

    private SizeEnum size;

    @Positive(message = "weight must be positive")
    private Float weight;

    @PositiveOrZero(message = "worth must be positive or zero")
    private Double worth;

    @Positive(message = "contractId must be positive")
    private Long contractId;

    @Positive(message = "deliveryStateId must be positive")
    private Long deliveryStateId;

    @Positive(message = "version must be positive")
    private Long version;
}
