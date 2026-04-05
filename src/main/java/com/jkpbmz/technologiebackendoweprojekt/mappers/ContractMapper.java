package com.jkpbmz.technologiebackendoweprojekt.mappers;

import com.jkpbmz.technologiebackendoweprojekt.entities.Contract;
import com.jkpbmz.technologiebackendoweprojekt.projections.ContractSummaryDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContractMapper {
    ContractSummaryDTO toContractSummaryDTO(Contract contract);
}
