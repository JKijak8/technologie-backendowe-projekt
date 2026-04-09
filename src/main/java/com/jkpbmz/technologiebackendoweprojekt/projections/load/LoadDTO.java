package com.jkpbmz.technologiebackendoweprojekt.projections.load;

import com.jkpbmz.technologiebackendoweprojekt.enums.SizeEnum;
import com.jkpbmz.technologiebackendoweprojekt.projections.contract.ContractSummaryDTO;
import com.jkpbmz.technologiebackendoweprojekt.projections.delivery_state.DeliveryStateDTO;
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
