package ada.grupo5.msfinanceiro.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ada.grupo5.msfinanceiro.entities.Bill;

public interface BillRepository extends JpaRepository<Bill, UUID>{
    
}
