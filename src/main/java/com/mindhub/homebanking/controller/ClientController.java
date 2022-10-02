package com.mindhub.homebanking.controller;
import com.mindhub.homebanking.dto.ClientDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClientController {
    public String getAccountNumberRandom() {
        Random rnd = new Random();
        int number = rnd.nextInt(99999999);
        return "VIN" + String.format("%08d", number);
    }
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AccountRepository accountRepository;
    @PostMapping("/clients")
    public ResponseEntity<Object> register(
            @RequestParam String firstName, @RequestParam String lastName,
            @RequestParam String email, @RequestParam String password) {

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

        if (clientRepository.findByEmail(email) != null) {
            return new ResponseEntity<>("Email already in use", HttpStatus.FORBIDDEN);
        }
        if (email.contains("@admin")){
            return new ResponseEntity<>("Cannot register as user", HttpStatus.FORBIDDEN);
        }
        LocalDateTime actualDateTime = LocalDateTime.now();
        String accountNumberNew = this.getAccountNumberRandom();
        while (true) {
            if(accountRepository.findByaccountNumber(accountNumberNew) == null){
                break;
            }
            else {
                accountNumberNew = this.getAccountNumberRandom();
            }
        }
        Account accountNew = new Account(accountNumberNew, 0.0, actualDateTime);
        Client clientNew = new Client(firstName, lastName, email, passwordEncoder.encode(password));
        clientRepository.save(clientNew);
        clientNew.add_account(accountNew);
        accountRepository.save(accountNew);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }
    @GetMapping("/clients/current")
    public ClientDTO getAll(Authentication authentication) {

        return new ClientDTO(clientRepository.findByEmail(authentication.getName()));

    }
}
