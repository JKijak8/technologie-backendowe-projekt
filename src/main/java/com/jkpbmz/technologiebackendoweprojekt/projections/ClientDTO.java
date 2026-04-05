package com.jkpbmz.technologiebackendoweprojekt.projections;

import lombok.Data;

import java.util.List;

@Data
public class ClientDTO {
    private Long id;
    private String name;
    private String nip;
    private String phoneNumber;
    private String email;
    private List<ContractSummaryDTO> contractSummaries;
}
