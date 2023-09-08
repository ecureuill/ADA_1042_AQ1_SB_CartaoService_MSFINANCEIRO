package ada.grupo5.msfinanceiro.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ada.grupo5.msfinanceiro.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByCpf(String cpf);
    
}
