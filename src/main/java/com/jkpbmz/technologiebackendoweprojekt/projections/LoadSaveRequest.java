package com.jkpbmz.technologiebackendoweprojekt.projections;

import com.jkpbmz.technologiebackendoweprojekt.enums.SizeEnum;
import lombok.Data;

@Data
public class LoadSaveRequest {
    private String identifier;
    private String type;
    private SizeEnum size;
    private Float weight;
    private Double worth;
    private Long contractId;
    private Long deliveryStateId;
}
