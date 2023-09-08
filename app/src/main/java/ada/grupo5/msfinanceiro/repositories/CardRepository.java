package ada.grupo5.msfinanceiro.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ada.grupo5.msfinanceiro.entities.Card;


public interface CardRepository extends JpaRepository<Card, Long> {
    
    List<Card> findByHolderId(String holder_id);
    Optional<Card> findByCardNumber(String card_number);

    Card findFirstByHolderId(String id);
}
