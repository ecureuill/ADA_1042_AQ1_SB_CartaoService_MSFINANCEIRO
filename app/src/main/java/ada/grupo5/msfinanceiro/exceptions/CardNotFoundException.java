package ada.grupo5.msfinanceiro.exceptions;

public class CardNotFoundException extends Exception{

    public CardNotFoundException(String cardNumber) {
        super("Card with number " + cardNumber + " not found");
    }

}
