package com.jkpbmz.technologiebackendoweprojekt.projections;

import com.jkpbmz.technologiebackendoweprojekt.enums.SizeEnum;
import lombok.Data;

@Data
public class LoadDTO {
    private Long id;
    private String identifier;
    private String type;
    private SizeEnum size;
    private Float weight;
    private Double worth;
    private ContractSummaryDTO contract;
    private DeliveryStateDTO deliveryState;
    private Long courseId;
}
