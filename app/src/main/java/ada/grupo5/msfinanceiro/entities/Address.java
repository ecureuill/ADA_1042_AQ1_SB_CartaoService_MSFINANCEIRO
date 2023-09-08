package ada.grupo5.msfinanceiro.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Embeddable
@AllArgsConstructor
public class Address {
    private String zip_code;
    private String street;
    private String neighborhood;
    private String city;
    private String state;
    private String country;
    private String number;
    private String complement;
}
