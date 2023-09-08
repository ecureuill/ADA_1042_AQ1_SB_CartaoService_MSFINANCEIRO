package ada.grupo5.msfinanceiro.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ada.grupo5.msfinanceiro.dtos.PurchaseDTO;
import ada.grupo5.msfinanceiro.dtos.PurchaseStatus;
import ada.grupo5.msfinanceiro.entities.Purchase;
import ada.grupo5.msfinanceiro.entities.Card;
import ada.grupo5.msfinanceiro.repositories.CardRepository;
import ada.grupo5.msfinanceiro.repositories.PurchaseRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final CardRepository cardRepository;

    public Purchase registerPayment(PurchaseDTO dto) throws Exception {
        Purchase purchase = convertToEntity(dto);
        purchaseRepository.save(purchase);

        return purchase;
    }

    public Purchase convertToEntity(PurchaseDTO dto) throws Exception {
        Purchase purchase = new Purchase();
        purchase.setDate(dto.purchaseDate().toLocalDate());
        purchase.setValue(new Double(dto.price().toString()));
        purchase.setStore(dto.store());

        Card card = cardRepository.findByCard_number(dto.cardNumber()).orElseThrow();
        purchase.setCard(card);

        purchase.setCreated_at(LocalDateTime.now());
        purchase.setDate(LocalDate.now());
        purchase.setProcessed(true);
        purchase.setStatus(PurchaseStatus.CONFIRMED);

        return purchase;
    }

    public Purchase savePurchase(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

}
