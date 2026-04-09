package com.jkpbmz.technologiebackendoweprojekt.mappers;

import com.jkpbmz.technologiebackendoweprojekt.entities.Contract;
import com.jkpbmz.technologiebackendoweprojekt.entities.Load;
import com.jkpbmz.technologiebackendoweprojekt.projections.contract.ContractSummaryDTO;
import com.jkpbmz.technologiebackendoweprojekt.projections.load.LoadDTO;
import com.jkpbmz.technologiebackendoweprojekt.projections.load.LoadSaveRequest;
import com.jkpbmz.technologiebackendoweprojekt.projections.load.LoadSummaryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {ContractMapper.class, DeliveryStateMapper.class})
public interface LoadMapper {
    ContractMapper CONTRACT_MAPPER = Mappers.getMapper(ContractMapper.class);

    @Mapping(source = "course.id", target = "courseId")
    LoadDTO toLoadDTO(Load load);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "contract", ignore = true)
    @Mapping(target = "deliveryState", ignore = true)
    @Mapping(target = "course", ignore = true)
    @Mapping(target = "sendDate", ignore = true)
    @Mapping(target = "deliveryDate", ignore = true)
    @Mapping(target = "deliveryTime", ignore = true)
    Load toLoad(LoadSaveRequest request);

    @Mapping(source = "contract", target = "contract", qualifiedByName = "getContractSummary")
    LoadSummaryDTO toLoadSummaryDTO(Load load);

    @Named("getContractSummary")
    static ContractSummaryDTO getContractSummary(Contract contract) {
        return CONTRACT_MAPPER.toContractSummaryDTO(contract);
    }
}
