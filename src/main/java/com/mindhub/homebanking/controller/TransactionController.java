package com.mindhub.homebanking.controller;

import com.mindhub.homebanking.dto.AccountDTO;
import com.mindhub.homebanking.dto.TransactionDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TransactionType;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TransactionController {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/transactions/{id}")
    public Optional<TransactionDTO> get_transaction(@PathVariable long id) {
        return transactionRepository.findById(id).map(TransactionDTO::new);
    }
    
    @Transactional
    @PostMapping("/transactions")
    public ResponseEntity<Object> register(@RequestParam String sourceAccount,
                                           @RequestParam String destinationAccount,
                                           @RequestParam Double amount,
                                           @RequestParam String description,
                                           Authentication authentication) {
        if (sourceAccount.isEmpty() || destinationAccount.isEmpty() || amount == 0.0 || description.isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }
        if (sourceAccount.equals(destinationAccount)){
            return new ResponseEntity<>("Cannot send money to same account", HttpStatus.FORBIDDEN);
        }
        Client clientAuth = clientRepository.findByEmail(authentication.getName());
        Account querySourceAccount = accountRepository.findByaccountNumber(sourceAccount);
        Account queryDestinationAccount = accountRepository.findByaccountNumber(destinationAccount);
        if (queryDestinationAccount == null) {
            return new ResponseEntity<>("Destination account not found", HttpStatus.FORBIDDEN);
        }
        if (querySourceAccount == null) {
            return new ResponseEntity<>("Source account not found", HttpStatus.FORBIDDEN);
        }
        if (!(clientAuth.getAccountSet().contains(querySourceAccount))) {
            return new ResponseEntity<>("You're not the owner of the source account", HttpStatus.FORBIDDEN);
        }
        if (querySourceAccount.getBalance() < amount) {
            return new ResponseEntity<>("Don't have enough balance in this account", HttpStatus.FORBIDDEN);
        }
        LocalDate dateNow = LocalDate.now();
        Transaction transactionDebit = new Transaction(-amount, description, dateNow, TransactionType.DEBIT);
        Transaction transactionCredit = new Transaction(amount, description, dateNow, TransactionType.CREDIT);
        querySourceAccount.addTransaction(transactionDebit);
        queryDestinationAccount.addTransaction(transactionCredit);
        transactionRepository.save(transactionDebit);
        transactionRepository.save(transactionCredit);
        querySourceAccount.setBalance(querySourceAccount.getBalance() - amount);
        queryDestinationAccount.setBalance(queryDestinationAccount.getBalance() + amount);

        return new ResponseEntity<>(HttpStatus.CREATED);


    }

}
