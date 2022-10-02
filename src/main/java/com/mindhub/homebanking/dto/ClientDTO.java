package com.mindhub.homebanking.dto;

import com.mindhub.homebanking.models.Client;

import java.util.Set;
import java.util.stream.Collectors;

public class ClientDTO {
    private String firstName, lastName, email;
    private long id;
    private Set<AccountDTO> accounts;
    private Set<ClientLoanDTO> loans;
    private Set<CardDTO> cards;
    public ClientDTO() {
    }

    public ClientDTO(Client client) {
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.email = client.getEmail();
        this.id = client.getId();
        this.accounts = client.getAccountSet().stream().map(AccountDTO::new).collect(Collectors.toSet());
        this.loans = client.getClientLoans().stream().map(ClientLoanDTO::new).collect(Collectors.toSet());
        this.cards = client.getCardSet().stream().map(CardDTO::new).collect(Collectors.toSet());
    }


    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public String getEmail() {
        return email;
    }


    public long getId() {
        return id;
    }

    public Set<AccountDTO> getAccounts() {
        return accounts;
    }

    public Set<ClientLoanDTO> getLoans() {
        return loans;
    }

    public Set<CardDTO> getCards() {
        return cards;
    }
}
