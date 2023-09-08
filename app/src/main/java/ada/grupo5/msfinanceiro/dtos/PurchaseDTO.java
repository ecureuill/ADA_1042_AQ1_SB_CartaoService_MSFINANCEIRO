package ada.grupo5.msfinanceiro.dtos;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PurchaseDTO {
    private String card_number;
    private String store;
    private Double value;
    private LocalDateTime date; 
}
