package ada.grupo5.msfinanceiro.services;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import ada.grupo5.msfinanceiro.converter.BillConverter;
import ada.grupo5.msfinanceiro.dtos.BillDTO;
import ada.grupo5.msfinanceiro.entities.Bill;
import ada.grupo5.msfinanceiro.entities.Card;
import ada.grupo5.msfinanceiro.repositories.BillRepository;
import ada.grupo5.msfinanceiro.repositories.CardRepository;
import ada.grupo5.msfinanceiro.repositories.PurchaseRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class BillService {

    private Integer generateBillXDaysBeforeDueDate = 10;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private BillConverter billConverter;

    public Optional<Bill> getBillById(UUID id) {
        return billRepository.findById(id);
    }

    @Scheduled(cron = "0 0 8 * * ?", zone="America/Sao_Paulo")
    public void generateBill() {
        LocalDate currentDate = LocalDate.now();
        List<Card> allCards = cardRepository.findAll();

        for (Card card : allCards) {
            
            LocalDate nextDueDate = (card.getBill_due_day() <= currentDate.getDayOfMonth()) ? 
                                         currentDate.plusMonths(1).withDayOfMonth(card.getBill_due_day()) : 
                                         currentDate.withDayOfMonth(card.getBill_due_day());
        
            if (nextDueDate.minusDays(generateBillXDaysBeforeDueDate).isEqual(currentDate)) {
                Double totalValue = purchaseRepository.sumValueByCardNumber(card.getCard_number())
                                                       .orElse(0.0);
        
                Bill bill = new Bill();
                bill.setValue(totalValue);
                bill.setCard(card);
                billRepository.save(bill);
            }
        }
        
    }

    public Optional<Bill> getLastBillForCard(String card_number) {
        return billRepository.getLastBillFromCard(card_number);
    }

    public List<BillDTO> findAllBillsForHolder(String holder_id) {

        List<Card> cards = cardRepository.findByHolderId(holder_id);
        
        List<Bill> bills = billRepository.findByCardIn(cards);
        
        return bills.stream().map(billConverter::convertToBillDTO).collect(Collectors.toList());
    }
    

    public List<BillDTO> findAllBillsByCardNumber(String card_number) {
        Optional<Card> optionalCard = cardRepository.findByCardNumber(card_number);
        
        if (!optionalCard.isPresent()) {
            return Collections.emptyList();
        }
        
        Card card = optionalCard.get();
        
        List<Bill> bills = billRepository.findByCard(card);
        
        return bills.stream()
                      .map(billConverter::convertToBillDTO)
                      .collect(Collectors.toList());
    }
    

    public BillDTO getBillForSpecificMonthAndYear(String card_number, int month, int year) {
    Card card = cardRepository.findByCardNumber(card_number)
            .orElseThrow(() -> new EntityNotFoundException("Cartao não encontrado"));

    Bill bill = billRepository.findByCardAndMonthYear(card, month, year)
            .orElseThrow(() -> new EntityNotFoundException("Fatura inexistente para o período selecionado"));

    return billConverter.convertToBillDTO(bill);
}
    
}
