package com.jkpbmz.technologiebackendoweprojekt.mappers;

import com.jkpbmz.technologiebackendoweprojekt.entities.Contract;
import com.jkpbmz.technologiebackendoweprojekt.projections.contract.ContractDTO;
import com.jkpbmz.technologiebackendoweprojekt.projections.contract.ContractSummaryDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContractMapper {
    ContractSummaryDTO toContractSummaryDTO(Contract contract);

    ContractDTO toContractDTO(Contract contract);
}
