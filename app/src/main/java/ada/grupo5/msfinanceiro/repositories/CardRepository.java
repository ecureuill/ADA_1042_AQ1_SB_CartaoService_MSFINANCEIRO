package ada.grupo5.msfinanceiro.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ada.grupo5.msfinanceiro.entities.Card;


public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> findByCustomerIdAndActive(Long customerId, Boolean active);

    @Query("SELECT c FROM Card c WHERE c.card_number = :cardNumber")
    Optional<Card> findByCardNumber(@Param("cardNumber") String cardNumber);
    
    List<Card> findByCustomerId(Long customerId);

    Card findFirstByCustomer_Id(Long customerId);

}
