package ada.grupo5.msfinanceiro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ada.grupo5.msfinanceiro.entities.Card;


public interface CardRepository extends JpaRepository<Card, Long> {
    
}
