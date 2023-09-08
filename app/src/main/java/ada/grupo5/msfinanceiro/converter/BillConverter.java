package ada.grupo5.msfinanceiro.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ada.grupo5.msfinanceiro.dtos.BillDTO;
import ada.grupo5.msfinanceiro.dtos.CustomPurchaseDTO;
import ada.grupo5.msfinanceiro.entities.Bill;
import ada.grupo5.msfinanceiro.entities.Purchase;
import ada.grupo5.msfinanceiro.repositories.PurchaseRepository;

@Component
public class BillConverter {
    
    private PurchaseRepository purchaseRepository;

    public BillDTO convertToBillDTO(Bill bill) {
        BillDTO dto = new BillDTO();
        
        dto.setValue(bill.getValue());
        dto.setCreated_at(bill.getCreated_at().toLocalDate());
        dto.setDue_date(bill.getDue_date());
        dto.setProcessed_date(bill.getProcessing_date());
        dto.setPaid_value(bill.getPaid_value());
        dto.setCard_number(bill.getCard_number());
        
        List<Purchase> purchases = purchaseRepository.findByCard(bill.getCard());
        List<CustomPurchaseDTO> purchaseDTOs = purchases.stream()
            .map(this::convertToPurchaseDTO)
            .collect(Collectors.toList());
        
        dto.setPurchase_summary(purchaseDTOs);
        
        return dto;
    }

    public CustomPurchaseDTO convertToPurchaseDTO(Purchase purchase) {
        CustomPurchaseDTO dto = new CustomPurchaseDTO();

        dto.setCard_number(purchase.getCard().getCard_number());
        dto.setStore(purchase.getStore());
        dto.setValue(purchase.getValue());
        dto.setDate(purchase.getDate());

        return dto;
    }
}
