package com.jkpbmz.technologiebackendoweprojekt.controllers;

import com.jkpbmz.technologiebackendoweprojekt.projections.*;
import com.jkpbmz.technologiebackendoweprojekt.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("/client")
public class ClientController {
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
}
