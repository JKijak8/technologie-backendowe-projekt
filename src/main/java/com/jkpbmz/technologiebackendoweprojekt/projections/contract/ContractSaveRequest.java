package com.jkpbmz.technologiebackendoweprojekt.projections.contract;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ContractSaveRequest {
    @NotBlank(message = "name is required")
    @Size(max = 100, message = "name must be at most 100 characters long")
    private String name;

    @NotBlank(message = "senderAdress is required")
    @Size(max = 255, message = "senderAddress must be at most 255 characters long")
    private String senderAddress;

    @NotBlank(message = "deliveryAddress is required")
    @Size(max = 255, message = "deliveryAddress must be at most 255 characters long")
    private String deliveryAddress;

    @NotNull(message = "clientId is required")
    @Positive(message = "clientId must be positive")
    private Long clientId;

    @Positive(message = "version number must be positive")
    private Long version;
}
