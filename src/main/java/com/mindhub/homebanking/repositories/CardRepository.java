package com.mindhub.homebanking.repositories;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.ClientLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CardRepository extends JpaRepository<Card, Long> {
    Card findByCardTypeAndCardColorAndClientId(Enum cardType, Enum cardColor, long clientId);
    Card findById(long id);
//    Card findByCardTypeAndCardColor(Enum cardType, Enum cardColor);
}