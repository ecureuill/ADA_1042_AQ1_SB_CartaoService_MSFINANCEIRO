package ada.grupo5.msfinanceiro.services;
import java.util.List;

import org.hibernate.validator.constraints.br.CPF;

import ada.grupo5.msfinanceiro.dtos.CardType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCardUser(
   @CPF 
   @NotNull
   String cpf,
   @NotBlank
   String name,
   @Valid
   @NotNull
   AddressDTO address,
   @NotNull
   CardType creditCardType
) {

}
