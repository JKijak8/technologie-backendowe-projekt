package com.jkpbmz.technologiebackendoweprojekt.projections.contract;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ContractSaveRequest {
    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "senderAdress is required")
    private String senderAddress;

    @NotBlank(message = "deliveryAddress is required")
    private String deliveryAddress;

    @NotNull(message = "clientId is required")
    @Positive(message = "clientId must be positive")
    private Long clientId;

    @Positive(message = "version number must be positive")
    private Long version;
}
