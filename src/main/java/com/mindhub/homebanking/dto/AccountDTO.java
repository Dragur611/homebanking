package com.mindhub.homebanking.dto;

import com.mindhub.homebanking.models.Account;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountDTO {
    private long id;
    private String accountNumber;
    private Double balance;
    private LocalDateTime creationDate;
    private Set<TransactionDTO> transactionsList;

    public AccountDTO() {
    }

    public AccountDTO(Account account) {
        this.id = account.getId();
        this.accountNumber = account.getAccountNumber();
        this.balance = account.getBalance();
        this.creationDate = account.getCreationDate();
        this.transactionsList = account.getTransactionSet().stream().map(TransactionDTO::new).collect(Collectors.toSet());
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

    public Set<TransactionDTO> getTransactionsList() {
        return transactionsList;
    }
}
