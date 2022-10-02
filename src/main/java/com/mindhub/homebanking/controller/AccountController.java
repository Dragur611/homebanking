package com.mindhub.homebanking.controller;

import com.mindhub.homebanking.dto.AccountDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ClientController clientController;
    @GetMapping("/accounts")
    public Set<AccountDTO> get_all_accounts() {
        return accountRepository.findAll().stream().map(AccountDTO::new).collect(Collectors.toSet());
    }
    @GetMapping("/accounts/{id}")
    public AccountDTO getAccountById(@PathVariable Long id, Authentication authentication){
        Client authClient = clientRepository.findByEmail(authentication.getName());
        Optional<Account> account = accountRepository.findById(id);

        if(authClient.getAccountSet().contains(account)) {
            return accountRepository.findById(id).map(AccountDTO::new).orElse(null);
        }else{
            return null;
        }
    }
//    @RequestMapping(path = "/clients/current/accounts", method = RequestMethod.POST)
//    public ResponseEntity<Object> register(Client client) {
//
//        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
//            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
//        }
//
//        if (clientRepository.findByEmail(email) != null) {
//            return new ResponseEntity<>("Email already in use", HttpStatus.FORBIDDEN);
//        }
//
//        clientRepository.save(new Client(firstName, lastName, email, passwordEncoder.encode(password)));
//        return new ResponseEntity<>(HttpStatus.CREATED);
//
//    }
    @PostMapping("/clients/current/accounts")
    public ResponseEntity<Object> register(Authentication authentication) {
        Client clientAuth = clientRepository.findByEmail(authentication.getName());
        if(clientAuth.getAccountSet().size() < 3){
            LocalDateTime actualDateTime = LocalDateTime.now();
            String accountNumberNew = clientController.getAccountNumberRandom();
            while (true) {
                if(accountRepository.findByaccountNumber(accountNumberNew) == null){
                    break;
                }
                else {
                    accountNumberNew = clientController.getAccountNumberRandom();
                }
            }
            Account accountNew = new Account(accountNumberNew, 0.0, actualDateTime);
            clientAuth.add_account(accountNew);
            accountRepository.save(accountNew);

        } else{
            return new ResponseEntity<>("Cannot have more than 3 accounts", HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);


    }

}
