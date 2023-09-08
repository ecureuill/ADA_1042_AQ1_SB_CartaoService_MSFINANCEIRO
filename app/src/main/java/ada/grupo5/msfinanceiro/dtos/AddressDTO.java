package ada.grupo5.msfinanceiro.dtos;

import jakarta.validation.constraints.NotBlank;

public record AddressDTO(
    @NotBlank
    String street,
    @NotBlank
    String city,
    @NotBlank
    String state,
    @NotBlank
    String country,
    @NotBlank
    String zipCode,
    @NotBlank
    String number,
    @NotBlank
    String neighborhood,
    String complement
) {

}
