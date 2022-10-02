package com.mindhub.homebanking.services.implement;

import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;

public class ClientServiceImplement implements ClientService {
    @Autowired
    ClientRepository clientRepository;
    @Override
    public void deleteClient(Client client) {
        clientRepository.delete(client);

    }

    @Override
    public void deleteClientById(long id) {
        clientRepository.deleteById(id);
    }
}
