package com.jkpbmz.technologiebackendoweprojekt.projections.contract;

import com.jkpbmz.technologiebackendoweprojekt.projections.client.ClientSummaryDTO;
import lombok.Data;

@Data
public class ContractDTO {
    private Long id;
    private String name;
    private String senderAddress;
    private String deliveryAddress;
    private ClientSummaryDTO client;
}
