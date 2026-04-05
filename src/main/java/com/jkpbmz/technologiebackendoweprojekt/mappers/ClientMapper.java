package com.jkpbmz.technologiebackendoweprojekt.mappers;

import com.jkpbmz.technologiebackendoweprojekt.entities.Client;
import com.jkpbmz.technologiebackendoweprojekt.entities.Contract;
import com.jkpbmz.technologiebackendoweprojekt.projections.ClientDTO;
import com.jkpbmz.technologiebackendoweprojekt.projections.ClientSaveRequest;
import com.jkpbmz.technologiebackendoweprojekt.projections.ClientSummaryDTO;
import com.jkpbmz.technologiebackendoweprojekt.projections.ContractSummaryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ContractMapper CONTRACT_MAPPER = Mappers.getMapper(ContractMapper.class);

    @Mapping(source = "contracts", target = "contracts", qualifiedByName = "getContracts")
    ClientDTO toClientDTO(Client client);

    ClientSummaryDTO toClientSummaryDTO(Client client);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "contracts", ignore = true)
    Client toClient(ClientSaveRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "contracts", ignore = true)
    void updateClient(ClientSaveRequest request, @MappingTarget Client client);

    @Named("getContracts")
    static List<ContractSummaryDTO> getContracts(List<Contract> contracts) {
        if (contracts == null) return null;
        return contracts.stream().map(CONTRACT_MAPPER::toContractSummaryDTO).collect(Collectors.toList());
    }
}
