package com.mindhub.homebanking.controller;
import com.mindhub.homebanking.dto.LoanApplicationDTO;
import com.mindhub.homebanking.dto.LoanDTO;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class LoanController {
    @Autowired
    LoanRepository loanRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    ClientLoanRepository clientLoanRepository;

    @Transactional
    @PostMapping("/loans")
    public ResponseEntity<String> addClientLoan(@RequestBody LoanApplicationDTO loanApplicationDTO, Authentication authentication) {

        if(loanApplicationDTO.getDestinationAccount() == null
                || loanApplicationDTO.getPayments() <= 0
                || loanApplicationDTO.getAmount() <= 0
                || loanApplicationDTO.getIdLoan() <= 0) {

            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

        Loan loanQuery = loanRepository.findById(loanApplicationDTO.getIdLoan()).orElse(null);

        if(loanQuery == null) {
            return new ResponseEntity<>("Loan doesn't exist", HttpStatus.FORBIDDEN);
        }

        if(loanQuery.getMaxAmount() < loanApplicationDTO.getAmount()) {
            return new ResponseEntity<>("Maximum amount exceeded", HttpStatus.FORBIDDEN);
        }

        if(!loanQuery.getPayment().contains(loanApplicationDTO.getPayments())) {
            return new ResponseEntity<>("Wrong amount of payments", HttpStatus.FORBIDDEN);
        }

        Account destinationAccount = accountRepository.findByaccountNumber(loanApplicationDTO.getDestinationAccount());
        if(destinationAccount == null) {
            return new ResponseEntity<>("Destination account doesn't exist", HttpStatus.FORBIDDEN);
        }

        Client clientAuth = clientRepository.findByEmail(authentication.getName());

        if(!clientAuth.getAccountSet().contains(destinationAccount)) {
            return new ResponseEntity<>("You're not the owner of the destination account", HttpStatus.FORBIDDEN);
        }

        ClientLoan newClientLoan = new ClientLoan(loanApplicationDTO.getAmount()*1.2, loanApplicationDTO.getPayments(), clientAuth, loanQuery);
        clientLoanRepository.save(newClientLoan);

        Transaction newTransaction = new Transaction(loanApplicationDTO.getAmount(), loanQuery.getName() + " loan approved", LocalDate.now(), TransactionType.CREDIT);
        destinationAccount.addTransaction(newTransaction);
        destinationAccount.setBalance(destinationAccount.getBalance() + loanApplicationDTO.getAmount());
        transactionRepository.save(newTransaction);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @GetMapping("/loans")
    public Set<LoanDTO> getLoansInfo() {
        return loanRepository.findAll().stream().map(LoanDTO::new).collect(Collectors.toSet());

    }
}
