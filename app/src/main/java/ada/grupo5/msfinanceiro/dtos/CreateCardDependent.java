package ada.grupo5.msfinanceiro.dtos;

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
