package com.jkpbmz.technologiebackendoweprojekt.projections.contract;

import lombok.Data;

@Data
public class ContractSummaryDTO {
    private Long id;
    private String name;
    private Long clientId;
    private String clientName;
}
