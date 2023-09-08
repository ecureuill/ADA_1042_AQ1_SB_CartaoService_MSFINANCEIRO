package ada.grupo5.msfinanceiro.services;

public class CustomerAlreadyHaveActiveCardException extends Exception{
    
    public CustomerAlreadyHaveActiveCardException() {
        super("Customer already have an active card");
    }

}
