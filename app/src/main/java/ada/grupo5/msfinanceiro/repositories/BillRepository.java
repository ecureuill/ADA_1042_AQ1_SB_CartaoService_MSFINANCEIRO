package ada.grupo5.msfinanceiro.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ada.grupo5.msfinanceiro.entities.Bill;
import ada.grupo5.msfinanceiro.entities.Card;

public interface BillRepository extends JpaRepository<Bill, UUID>{
    @Query("SELECT b FROM Bill b WHERE b.card.card_number = :card_number ORDER BY b.createdAt DESC")
    Optional<Bill> getLastBillFromCard(String card_number);
    List<Bill> findByCardCardNumber(String card_number);
    List<Bill> findByCardIn(List<Card> cards);
    List<Bill> findByCard(Card card);
    
    @Query("SELECT b FROM Bill b WHERE b.card = :card AND MONTH(b.due_date) = :month AND YEAR(b.due_date) = :year")
    Optional<Bill> findByCardAndMonthYear(@Param("card") Card card, @Param("month") int month, @Param("year") int year);

    Optional<Bill> findById(UUID id);

}
