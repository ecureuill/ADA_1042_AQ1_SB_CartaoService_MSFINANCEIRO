package ada.grupo5.msfinanceiro.dtos;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.Valid;
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
