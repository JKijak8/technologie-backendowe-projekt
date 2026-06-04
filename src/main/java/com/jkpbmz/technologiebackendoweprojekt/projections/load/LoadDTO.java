package com.jkpbmz.technologiebackendoweprojekt.projections.load;

import com.jkpbmz.technologiebackendoweprojekt.enums.DeliveryStatesEnum;
import com.jkpbmz.technologiebackendoweprojekt.enums.SizeEnum;
import com.jkpbmz.technologiebackendoweprojekt.projections.contract.ContractSummaryDTO;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class LoadDTO {
    private Long id;
    private String identifier;
    private String type;
    private SizeEnum size;
    private Float weight;
    private Double worth;
    private ContractSummaryDTO contract;
    private DeliveryStatesEnum deliveryState;
    private Long courseId;
    private Long version;
    private String deliveryTime;
    private ZonedDateTime sendDate;
    private ZonedDateTime deliveryDate;
}
