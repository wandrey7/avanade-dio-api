package com.wdev.avanade_dio_api.dto;

import com.wdev.avanade_dio_api.model.Card;

import java.math.BigDecimal;
import java.util.Optional;

public record CardDto(
        Long id,
        String number,
        BigDecimal limit
) {
    public static CardDto fromEntity(Card card) {
        if (card == null) return null;

        return new CardDto(
                card.getId(),
                card.getNumber(),
                card.getLimit()
        );
    }

    public Card toEntity() {
        Card card = new Card();
        Optional.ofNullable(this.id).ifPresent(card::setId);
        card.setNumber(this.number);
        card.setLimit(this.limit);
        return card;
    }
}