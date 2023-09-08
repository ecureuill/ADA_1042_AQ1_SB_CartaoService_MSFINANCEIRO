package ada.grupo5.msfinanceiro.services;

public class CustomerHasNoActiveCardException extends Exception {

    public CustomerHasNoActiveCardException() {
        super("Can not add additional card. Titular customer has no active card");
    }
}
