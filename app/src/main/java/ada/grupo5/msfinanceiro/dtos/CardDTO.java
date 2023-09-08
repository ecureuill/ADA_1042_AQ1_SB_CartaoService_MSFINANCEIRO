package ada.grupo5.msfinanceiro.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

public record CardDTO(
    @NotBlank
    String cardNumber,
    @NotBlank
    String accountOwner,
    @NotBlank
    String cvv,
    @NotBlank
    LocalDate expireCardDate
) {
    
}
