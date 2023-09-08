package ada.grupo5.msfinanceiro.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ada.grupo5.msfinanceiro.entities.Card;
import ada.grupo5.msfinanceiro.entities.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, UUID> {
    @Query("SELECT SUM(p.value) FROM Purchase p WHERE p.card.card_number = :card_number")
    Optional<Double> sumValueByCardNumber(@Param("card_number") String card_number);

    List<Purchase> findByCard(Card card);
}
