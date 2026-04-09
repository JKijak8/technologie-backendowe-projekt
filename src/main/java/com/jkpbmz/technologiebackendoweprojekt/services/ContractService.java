package com.jkpbmz.technologiebackendoweprojekt.services;

import com.jkpbmz.technologiebackendoweprojekt.entities.Client;
import com.jkpbmz.technologiebackendoweprojekt.entities.Contract;
import com.jkpbmz.technologiebackendoweprojekt.exceptions.NotFoundException;
import com.jkpbmz.technologiebackendoweprojekt.mappers.ContractMapper;
import com.jkpbmz.technologiebackendoweprojekt.projections.contract.ContractDTO;
import com.jkpbmz.technologiebackendoweprojekt.projections.contract.ContractSaveRequest;
import com.jkpbmz.technologiebackendoweprojekt.projections.contract.ContractSummaryDTO;
import com.jkpbmz.technologiebackendoweprojekt.repositories.ClientRepository;
import com.jkpbmz.technologiebackendoweprojekt.repositories.ContractRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ContractService {

    private final ContractRepository contractRepository;
    private final ClientRepository clientRepository;
    private final ContractMapper contractMapper;

    public ContractDTO fetchContract(Long contractId) {
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new NotFoundException("Contract not found"));
        return contractMapper.toContractDTO(contract);
    }

    public Page<ContractSummaryDTO> fetchContractList(String clientName, String city, Pageable pageable) {
        Page<Contract> contracts = contractRepository.findAll(pageable);
        return contracts.map(contractMapper::toContractSummaryDTO);
    }

    @Transactional
    public ContractDTO createContract(ContractSaveRequest request) {
        Client client = clientRepository.findById(request.getClientId())
                .orElseThrow(() -> new NotFoundException("Client not found"));

        Contract contract = new Contract();
        contract.setName(request.getName());
        contract.setSenderAddress(request.getSenderAddress());
        contract.setDeliveryAddress(request.getDeliveryAddress());
        contract.setClient(client);

        contractRepository.save(contract);
        return contractMapper.toContractDTO(contract);
    }

    @Transactional
    public ContractDTO updateContract(Long contractId, ContractSaveRequest request) {
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new NotFoundException("Contract not found"));

        Client client = clientRepository.findById(request.getClientId())
                .orElseThrow(() -> new NotFoundException("Client not found"));

        contract.setName(request.getName());
        contract.setSenderAddress(request.getSenderAddress());
        contract.setDeliveryAddress(request.getDeliveryAddress());
        contract.setClient(client);

        contractRepository.save(contract);
        return contractMapper.toContractDTO(contract);
    }

    public void deleteContract(Long contractId) {
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new NotFoundException("Contract not found"));
        contractRepository.delete(contract);
    }
}
