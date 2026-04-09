package com.jkpbmz.technologiebackendoweprojekt.controllers;

import com.jkpbmz.technologiebackendoweprojekt.exceptions.BadRequestException;
import com.jkpbmz.technologiebackendoweprojekt.projections.client.ClientDTO;
import com.jkpbmz.technologiebackendoweprojekt.projections.client.ClientSaveRequest;
import com.jkpbmz.technologiebackendoweprojekt.projections.client.ClientSummaryDTO;
import com.jkpbmz.technologiebackendoweprojekt.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("/client")
public class ClientController {
    private static final int MAX_PAGE_SIZE = 25;

    private final ClientService clientService;

    @GetMapping("")
    public ClientDTO findClient(@RequestParam("clientId") Long clientId) {
        return clientService.fetchClient(clientId);
    }

    @PostMapping("")
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientSaveRequest request,
                                                  UriComponentsBuilder uriBuilder) {
        ClientDTO clientDTO = clientService.createClient(request);
        URI uri = uriBuilder.path("/client?clientId={id}").buildAndExpand(clientDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(clientDTO);
    }

    @PutMapping("")
    public ClientDTO updateClient(@RequestParam("clientId") Long clientId,
                                  @RequestBody ClientSaveRequest request) {
        return clientService.updateClient(clientId, request);
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteClient(@RequestParam("clientId") Long clientId) {
        clientService.deleteClient(clientId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/list")
    public Page<ClientSummaryDTO> getClientList(Pageable pageable) {
        if (pageable.getPageSize() > MAX_PAGE_SIZE) {
            throw new BadRequestException("Page size exceeds maximum. Maximum allowed is " + MAX_PAGE_SIZE);
        }

        return clientService.fetchClientList(pageable);
    }
}
