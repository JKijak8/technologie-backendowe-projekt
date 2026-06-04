package com.jkpbmz.technologiebackendoweprojekt.projections.load;

import com.jkpbmz.technologiebackendoweprojekt.enums.DeliveryStatesEnum;
import com.jkpbmz.technologiebackendoweprojekt.projections.contract.ContractSummaryDTO;
import lombok.Data;

@Data
public class LoadSummaryDTO {
    private Long id;
    private String identifier;
    private ContractSummaryDTO contract;
    private DeliveryStatesEnum deliveryState;
}
