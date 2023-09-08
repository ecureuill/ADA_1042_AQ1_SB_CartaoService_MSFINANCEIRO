package ada.grupo5.msfinanceiro.exceptions;

public class CustomerNotFoundException extends Exception{

    public CustomerNotFoundException(String cpf) {
        super("Customer with CPF " + cpf + "not found");
    }
}
