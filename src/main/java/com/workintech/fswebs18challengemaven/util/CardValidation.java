package com.workintech.fswebs18challengemaven.util;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.entity.Type;
import com.workintech.fswebs18challengemaven.exceptions.CardException;
import org.springframework.http.HttpStatus;

public class CardValidation {

    public static void validateCard(Card card) {
        // Kural 1: Hem type hem value dolu olamaz
        if (card.getType() != null && card.getValue() != null) {
            throw new CardException("A card cannot have both a type and a value.", HttpStatus.BAD_REQUEST);
        }

        // Kural 2: JOKER ise value ve color null olmalı
        if (card.getType() != null && card.getType().equals(Type.JOKER)) {
            if (card.getValue() != null || card.getColor() != null) {
                throw new CardException("Joker card must have null value and null color.", HttpStatus.BAD_REQUEST);
            }
        }

        // Kural 3: Type yoksa Value olmalı (opsiyonel ama mantıklı bir ekleme)
        if (card.getType() == null && card.getValue() == null) {
            throw new CardException("A card must have either a type or a value.", HttpStatus.BAD_REQUEST);
        }
    }
}