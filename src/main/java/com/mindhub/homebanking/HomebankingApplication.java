package com.mindhub.homebanking;
import com.mindhub.homebanking.dto.CardCombinationDTO;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@SpringBootApplication
public class HomebankingApplication {

	@Autowired
	private PasswordEncoder encoder;


	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}
	@Bean
	public CommandLineRunner init_data(ClientRepository client_repository, AccountRepository account_repository, TransactionRepository transaction_repository, LoanRepository loanRepository, ClientLoanRepository clientLoanRepository, CardRepository cardRepository) {
		return (args) -> {
//			String pw1 = "pw123";
//			String pw2 = "pw321";
//
//
//			// save a couple of customers
//			Client client1 = new Client("Jack", "Bauer", "emailprueba1@admin.com", encoder.encode(pw1));
//			Client client2 = new Client("Chloe", "O'Brian", "emailprueba2@mindhub.com", encoder.encode(pw2));
//			client_repository.save(client1);
//			client_repository.save(client2);
//			LocalDateTime actual_date_time = LocalDateTime.now();
//			Account account1 = new Account("VIN001", 1000.32, actual_date_time);
//			client1.add_account(account1);
//			account_repository.save(account1);
//			//
//			Account account2 = new Account("VIN002", 100.25, actual_date_time);
//			client1.add_account(account2);
//			account_repository.save(account2);
//			//
//			Account account3 = new Account("VIN003", 123.58, actual_date_time);
//			client2.add_account(account3);
//			account_repository.save(account3);
//
//			//
//			LocalDate date = LocalDate.now();
//			LocalDate date2 = LocalDate.now().plusDays(2);
//			LocalDate date3 = LocalDate.now().plusDays(5);
//
//
//			Transaction transaction1 = new Transaction(-1500.00, "Alquiler", date2, TransactionType.DEBIT);
//			account1.addTransaction(transaction1);
//			transaction_repository.save(transaction1);
//
//			Transaction transaction2 =
//					new Transaction(-200.00, "Servicios",
//							date, TransactionType.DEBIT);
//			account1.addTransaction(transaction2);
//			transaction_repository.save(transaction2);
//
//			Transaction transaction3 =
//					new Transaction(-1000.00, "Servicios",
//							date, TransactionType.DEBIT);
//			account2.addTransaction(transaction3);
//			transaction_repository.save(transaction3);
//
//			Transaction transaction4 =
//					new Transaction(700, "Servicios",
//							date, TransactionType.CREDIT);
//			account3.addTransaction(transaction4);
//			transaction_repository.save(transaction4);
//
//			Transaction transaction5 =
//					new Transaction(5000, "Sueldo", date3,
//							TransactionType.CREDIT);
//			account1.addTransaction(transaction5);
//			transaction_repository.save(transaction5);
//
//			Set<Integer> payments = Set.of(60,40,24,12);
//			Set<Integer> payments2 = Set.of(48,24,12);
//			Set<Integer> payments3 = Set.of(80,60,48,24,12);
//			Loan loan1= new Loan("hipotecario",500000.0,payments);
//			Loan loan2= new Loan("personal", 200000.0,payments2);
//			Loan loan3= new Loan("automotriz", 100000.0,payments3);
//			loanRepository.save(loan1);
//			loanRepository.save(loan2);
//			loanRepository.save(loan3);
//			ClientLoan clientLoan1 = new ClientLoan(400000.0,60,client1,loan1);
//			ClientLoan clientLoan2 = new ClientLoan(50000.0,12,client1,loan2);
//			ClientLoan clientLoan3 = new ClientLoan(8000.0,24,client1,loan2);
//			ClientLoan clientLoan4 = new ClientLoan(8000.0,80,client1,loan3);
//			clientLoanRepository.save(clientLoan1);
//			clientLoanRepository.save(clientLoan2);
//			clientLoanRepository.save(clientLoan3);
//			clientLoanRepository.save(clientLoan4);
//			LocalDate fromDate1 = LocalDate.now();
//			LocalDate thruDate1= fromDate1.plusYears(5);
//
//			LocalDate fromDate2 = LocalDate.now().plusYears(2);
//			LocalDate thruDate2 = fromDate2.plusYears(5);
//			Card card1= new Card(client1.getFirstName()+" "+ client1.getLastName(), "3456-5573-5578-5678",fromDate1,thruDate1,344, CardColor.TITANIUM,CardType.CREDIT,client1);
//			Card card2= new Card(client1.getFirstName()+" "+ client1.getLastName(), "5306-9173-8367-3253",fromDate2,thruDate2,562, CardColor.GOLD,CardType.DEBIT,client1);
//			cardRepository.save(card1);
//			cardRepository.save(card2);

		};
	}
}
