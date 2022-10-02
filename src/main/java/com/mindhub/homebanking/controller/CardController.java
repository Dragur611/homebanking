package com.mindhub.homebanking.controller;

import com.mindhub.homebanking.dto.AccountDTO;
import com.mindhub.homebanking.dto.CardCombinationDTO;
import com.mindhub.homebanking.dto.ClientDTO;
import com.mindhub.homebanking.dto.ClientLoanDTO;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.CardRepository;
import com.mindhub.homebanking.repositories.ClientLoanRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.utils.CardUtils;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CardController {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ClientLoanRepository clientLoanRepository;

    @PostMapping("/clients/current/cards")
    public ResponseEntity<Object> register(@RequestParam CardType cardType,
                                           @RequestParam CardColor cardColor,
                                           Authentication authentication) {

        Client authClient = clientRepository.findByEmail(authentication.getName());
        Card queryRepeat = cardRepository.findByCardTypeAndCardColorAndClientId(cardType, cardColor, authClient.getId());
        if (queryRepeat != null){
            return new ResponseEntity<>("Cannot have repeated cards", HttpStatus.FORBIDDEN);
        }
        LocalDate fromDate = LocalDate.now();
        LocalDate thruDate= fromDate.plusYears(5);
        String cardHolder = authClient.getFirstName()+" "+ authClient.getLastName();
        String cardNumber = CardUtils.getRandomCardNumber();
        int cvv = CardUtils.getRandomCvv();
        Card cardNew = new Card(cardHolder, cardNumber, fromDate, thruDate, cvv, cardColor, cardType, authClient);
        cardRepository.save(cardNew);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @GetMapping("/cards/cardsinfo")
    public CardCombinationDTO getCardsInfo() {

        return new CardCombinationDTO();

    }
    @DeleteMapping("/clients/current/cards")
    public ResponseEntity<Object> register(@RequestParam long cardId, Authentication authentication) {
        Card cardToDelete = cardRepository.findById(cardId);
        Client authClient = clientRepository.findByEmail(authentication.getName());
        if (cardToDelete == null) {
            return new ResponseEntity<>("The card doesn't exist", HttpStatus.FORBIDDEN);
        }
        if(!authClient.getCardSet().contains(cardToDelete)) {
            return new ResponseEntity<>("You are not the owner of the card", HttpStatus.FORBIDDEN);
        }
        cardRepository.delete(cardToDelete);
        return new ResponseEntity<>(HttpStatus.OK);

    }
    @GetMapping("/clients/current/loans")
    public Set<ClientLoanDTO> getAllLoans(Authentication authentication) {
        Client authClient = clientRepository.findByEmail(authentication.getName());
        return authClient.getClientLoans().stream().map(ClientLoanDTO::new).collect(Collectors.toSet());
    }
}
