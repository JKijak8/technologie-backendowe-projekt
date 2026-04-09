package com.jkpbmz.technologiebackendoweprojekt.projections.client;

import lombok.Data;

@Data
public class ClientSummaryDTO {
    private Long id;
    private String name;
    private String nip;
    private String  deliveryAddress;
}
