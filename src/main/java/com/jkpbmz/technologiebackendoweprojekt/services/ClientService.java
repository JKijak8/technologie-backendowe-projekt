package com.jkpbmz.technologiebackendoweprojekt.services;

import com.jkpbmz.technologiebackendoweprojekt.entities.Client;
import com.jkpbmz.technologiebackendoweprojekt.exceptions.NotFoundException;
import com.jkpbmz.technologiebackendoweprojekt.mappers.ClientMapper;
import com.jkpbmz.technologiebackendoweprojekt.projections.ClientDTO;
import com.jkpbmz.technologiebackendoweprojekt.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ClientService {
    private ClientRepository clientRepository;

    private ClientMapper clientMapper;

    public ClientDTO fetchClient(Long clientId) {
        Client client = clientRepository.findById(clientId).orElse(null);
        if (client == null) {
            throw new NotFoundException("Client not found");
        }
        return clientMapper.toClientDTO(client);
    }
}
