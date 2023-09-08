package ada.grupo5.msfinanceiro.services;

public class CustomerNotFoundException extends Exception{

    public CustomerNotFoundException(String cpf) {
        super("Customer with CPF " + cpf + "not found");
    }
}
