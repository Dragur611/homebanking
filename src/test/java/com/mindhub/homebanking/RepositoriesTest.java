package com.mindhub.homebanking;

import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.util.List;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@SpringBootTest
@AutoConfigureTestDatabase(replace = NONE)
public class RepositoriesTest {
    @Autowired
    LoanRepository loanRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    ClientLoanRepository clientLoanRepository;

    @Test
    public void existLoans(){

        List<Loan> loans = loanRepository.findAll();
        assertThat(loans,is(not(empty())));

    }

    @Test
    public void existPersonalLoan(){

        List<Loan> loans = loanRepository.findAll();
        assertThat(loans, hasItem(hasProperty("name", is("personal"))));

    }
    @Test
    public void existClients(){
        List<Client> clients = clientRepository.findAll();
        assertThat(clients,is(not(empty())));
    }
    @Test
    public void existPipeClient(){
        List<Client> clients = clientRepository.findAll();
        assertThat(clients, hasItem(hasProperty("firstName", is("Jack"))));
    }
    @Test
    public void existAccounts(){
        List<Account> accounts = accountRepository.findAll();
        assertThat(accounts,is(not(empty())));
    }
    @Test
    public void existSingularAccount(){
        List<Account> accounts= accountRepository.findAll();
        assertThat(accounts, hasItem(hasProperty("accountNumber", is("VIN001"))));
    }
    @Test
    public void existCards(){
        List<Card> cards = cardRepository.findAll();
        assertThat(cards,is(not(empty())));
    }
    @Test
    public void existSingularCard(){
        List<Card> cards= cardRepository.findAll();
        assertThat(cards, hasItem(hasProperty("cardHolder", is("Jack Bauer"))));
    }
    @Test
    public void existTransactions(){
        List<Transaction> transactions = transactionRepository.findAll();
        assertThat(transactions,is(not(empty())));
    }
    @Test
    public void existSingularTransaction(){
        List<Transaction> transactions= transactionRepository.findAll();
        assertThat(transactions, hasItem(hasProperty("amount", is(5000.0))));
    }
//    @Test
//    public void cardNumberIsCreated(){
//
//        String cardNumber = CardUtils.getCardNumber();
//
//        assertThat(cardNumber,is(not(emptyOrNullString())));
//
//    }
}
