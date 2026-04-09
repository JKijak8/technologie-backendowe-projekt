package com.jkpbmz.technologiebackendoweprojekt.services;

import com.jkpbmz.technologiebackendoweprojekt.entities.Client;
import com.jkpbmz.technologiebackendoweprojekt.exceptions.ConflictException;
import com.jkpbmz.technologiebackendoweprojekt.exceptions.NotFoundException;
import com.jkpbmz.technologiebackendoweprojekt.mappers.ClientMapper;
import com.jkpbmz.technologiebackendoweprojekt.projections.client.ClientDTO;
import com.jkpbmz.technologiebackendoweprojekt.projections.client.ClientSaveRequest;
import com.jkpbmz.technologiebackendoweprojekt.projections.client.ClientSummaryDTO;
import com.jkpbmz.technologiebackendoweprojekt.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

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

    public Page<ClientSummaryDTO> fetchClientList(Pageable pageable) {
        Page<Client> clients = clientRepository.findAll(pageable);
        return clients.map(clientMapper::toClientSummaryDTO);
    }

    public ClientDTO createClient(ClientSaveRequest request) {
        checkIfNipExists(request.getNip());

        Client client = clientMapper.toClient(request);
        clientRepository.save(client);

        return clientMapper.toClientDTO(client);
    }

    public ClientDTO updateClient(Long clientId, ClientSaveRequest request) {
        Client client = clientRepository.findById(clientId).orElse(null);
        if (client == null) {
            throw new NotFoundException("Client not found");
        }

        if (!Objects.equals(request.getNip(), client.getNip())) {
            checkIfNipExists(request.getNip());
        }

        clientMapper.updateClient(request, client);
        clientRepository.save(client);

        return clientMapper.toClientDTO(client);
    }

    public void deleteClient(Long clientId) {
        Client client = clientRepository.findById(clientId).orElse(null);
        if (client == null) {
            throw new NotFoundException("Client not found");
        }
        clientRepository.delete(client);
    }

    private void checkIfNipExists (String nip) {
        if(clientRepository.existsByNip(nip)) {
            throw new ConflictException("Client with this NIP already exists.");
        }
    }
}
