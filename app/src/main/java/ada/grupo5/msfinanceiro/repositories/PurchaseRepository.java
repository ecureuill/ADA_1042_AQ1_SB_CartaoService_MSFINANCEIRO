package ada.grupo5.msfinanceiro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ada.grupo5.msfinanceiro.entities.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    
}
