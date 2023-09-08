package ada.grupo5.msfinanceiro.services;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.NotBlank;

public record CreateCardDependent(
    @NotBlank
    String name,
    @CPF
    @NotBlank
    String cpf,
    @NotBlank
    @CPF
    String customerCPF
) {

}
