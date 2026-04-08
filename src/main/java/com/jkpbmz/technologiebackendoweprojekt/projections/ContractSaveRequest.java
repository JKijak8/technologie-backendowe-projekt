package com.jkpbmz.technologiebackendoweprojekt.projections;

import lombok.Data;

@Data
public class ContractSaveRequest {
    private String name;
    private String senderAddress;
    private String deliveryAddress;
    private Long clientId;
}
