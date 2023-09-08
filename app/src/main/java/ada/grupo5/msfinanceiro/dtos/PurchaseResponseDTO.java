package ada.grupo5.msfinanceiro.dtos;

import ada.grupo5.msfinanceiro.entities.Purchase;

public record PurchaseResponseDTO(
    PurchaseStatus status
) {

    public PurchaseResponseDTO(Purchase purchase) {
        this(purchase.getStatus());
    }

}
