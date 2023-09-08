package ada.grupo5.msfinanceiro.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record PurchaseDTO(
    LocalDateTime purchaseDate,
    BigDecimal price,
    String store,
    String cardNumber,
    String cvv,
    LocalDate expireCardDate,
    String accountOwner
)
{
  
}
