package ada.grupo5.msfinanceiro.dtos;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class BillDTO {

    private String card_number;
    private Double value;
    private Double paid_value;
    private LocalDate created_at;
    private LocalDate due_date;
    private LocalDate processed_date;

    private List<CustomPurchaseDTO> purchase_summary;
    
}
