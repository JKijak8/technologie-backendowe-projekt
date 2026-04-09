package com.jkpbmz.technologiebackendoweprojekt.controllers;
import com.jkpbmz.technologiebackendoweprojekt.exceptions.BadRequestException;
import com.jkpbmz.technologiebackendoweprojekt.projections.contract.ContractDTO;
import com.jkpbmz.technologiebackendoweprojekt.projections.contract.ContractSaveRequest;
import com.jkpbmz.technologiebackendoweprojekt.projections.contract.ContractSummaryDTO;
import com.jkpbmz.technologiebackendoweprojekt.services.ContractService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("/contract")
public class ContractController {
    private static final int MAX_PAGE_SIZE = 25;

    private final ContractService contractService;

    @GetMapping("")
    public ContractDTO findContract(@RequestParam("contractId") Long contractId) {
        return contractService.fetchContract(contractId);
    }

    @GetMapping("/list")
    public Page<ContractSummaryDTO> findContractList(
            @RequestParam(required = false) String clientName,
            @RequestParam(required = false) String city,
            Pageable pageable) {

        if (pageable.getPageSize() > MAX_PAGE_SIZE) {
            throw new BadRequestException("Page size exceeds maximum. Maximum allowed is " + MAX_PAGE_SIZE);
        }

       return contractService.fetchContractList(clientName, city, pageable);
    }

    @PostMapping("")
    public ResponseEntity<ContractDTO> createContract(@RequestBody ContractSaveRequest request,
                                                      UriComponentsBuilder uriBuilder) {
        ContractDTO contractDTO = contractService.createContract(request);
        URI uri = uriBuilder.path("/contract?contractId={id}").buildAndExpand(contractDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(contractDTO);
    }

    @PutMapping("")
    public ContractDTO updateContract(@RequestParam("contractId") Long contractId,
                                      @RequestBody ContractSaveRequest request) {
        return contractService.updateContract(contractId, request);
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteContract(@RequestParam("contractId") Long contractId) {
        contractService.deleteContract(contractId);
        return ResponseEntity.noContent().build();
    }


}
