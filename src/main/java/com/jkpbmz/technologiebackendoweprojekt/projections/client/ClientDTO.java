package com.jkpbmz.technologiebackendoweprojekt.projections.client;

import com.jkpbmz.technologiebackendoweprojekt.projections.contract.ContractSummaryDTO;
import lombok.Data;

import java.util.List;

@Data
public class ClientDTO {
    private Long id;
    private String name;
    private String nip;
    private String phoneNumber;
    private String email;
    private List<ContractSummaryDTO> contracts;
}
