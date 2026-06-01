package com.jkpbmz.technologiebackendoweprojekt.mappers;

import com.jkpbmz.technologiebackendoweprojekt.entities.Contract;
import com.jkpbmz.technologiebackendoweprojekt.projections.contract.ContractDTO;
import com.jkpbmz.technologiebackendoweprojekt.projections.contract.ContractSummaryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContractMapper {
    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "client.name", target = "clientName")
    ContractSummaryDTO toContractSummaryDTO(Contract contract);

    ContractDTO toContractDTO(Contract contract);
}
