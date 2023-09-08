package ada.grupo5.msfinanceiro.dtos;

import java.time.LocalDate;

import ada.grupo5.msfinanceiro.entities.Card;

public record CardResponse(
    String number,
    CardType type,
    String cardHolder,
    LocalDate ExpirationDate,
    String CVV
) {

    public CardResponse(Card card) {
        this(card.getCard_number(), card.getType(), card.getCustomerName(), card.getExpiration_date(), card.getCvv());
    }}
