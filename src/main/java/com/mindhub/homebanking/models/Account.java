package com.mindhub.homebanking.models;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String accountNumber;
    private Double balance;
    private LocalDateTime creationDate;
    @OneToMany(mappedBy="account", fetch=FetchType.EAGER)
    private Set<Transaction> transactionSet = new HashSet<>();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="client_id")
    private Client client;

    public Account() {}

    public Account(String accountNumber, Double balance, LocalDateTime creationDate) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.creationDate = creationDate;
    }

    public long getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Set<Transaction> getTransactionSet() {
        return transactionSet;
    }

    public void addTransaction(Set<Transaction> transactionSet) {
        this.transactionSet = transactionSet;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }


    public void addTransaction(Transaction transaction){
        transaction.setAccount(this);
        transactionSet.add(transaction);
    }
}

