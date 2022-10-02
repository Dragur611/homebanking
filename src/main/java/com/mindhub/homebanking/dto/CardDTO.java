package com.mindhub.homebanking.dto;

import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.CardColor;
import com.mindhub.homebanking.models.CardType;
import com.mindhub.homebanking.models.Client;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

public class CardDTO {
    private long id;
    private String cardHolder, number;
    private int cvv;
    private LocalDate fromDate, thruDate;
    private CardColor cardColor;
    private CardType cardType;

    public CardDTO() {
    }

    public CardDTO(Card card) {
        this.id = card.getId();
        this.cardHolder = card.getCardHolder();
        this.number = card.getNumber();
        this.cvv = card.getCvv();
        this.fromDate = card.getFromDate();
        this.thruDate = card.getThruDate();
        this.cardColor = card.getCardColor();
        this.cardType = card.getCardType();
    }

    public long getId() {
        return id;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public String getNumber() {
        return number;
    }

    public int getCvv() {
        return cvv;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getThruDate() {
        return thruDate;
    }

    public CardColor getCardColor() {
        return cardColor;
    }

    public CardType getCardType() {
        return cardType;
    }
}
