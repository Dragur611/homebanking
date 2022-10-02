package com.mindhub.homebanking.services;

import com.mindhub.homebanking.models.Client;

public interface ClientService {
    void deleteClient(Client client);
    void deleteClientById(long id);
}
