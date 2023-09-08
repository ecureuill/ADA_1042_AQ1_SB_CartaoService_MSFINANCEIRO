package ada.grupo5.msfinanceiro.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ada.grupo5.msfinanceiro.entities.Card;


public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> findByIdAndActiveTrue(Long id);

    Optional<Card> findByCard_number(String cardNumber);
    
}
