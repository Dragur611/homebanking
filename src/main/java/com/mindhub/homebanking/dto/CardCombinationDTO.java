package com.mindhub.homebanking.dto;

import com.mindhub.homebanking.models.CardColor;
import com.mindhub.homebanking.models.CardType;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CardCombinationDTO {
    private List<String> cardTypes = Stream.of(CardType.values())
            .map(CardType::name)
            .collect(Collectors.toList());
    private List<String> cardColors = Stream.of(CardColor.values())
            .map(CardColor::name)
            .collect(Collectors.toList());
    public CardCombinationDTO() {
        this.cardTypes = cardTypes;
        this.cardColors = cardColors;
    }

    public List<String> getCardTypes() {
        return cardTypes;
    }


    public List<String> getCardColors() {
        return cardColors;
    }

}
