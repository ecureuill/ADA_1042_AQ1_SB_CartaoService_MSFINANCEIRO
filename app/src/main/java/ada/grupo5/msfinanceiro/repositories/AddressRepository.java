package ada.grupo5.msfinanceiro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ada.grupo5.msfinanceiro.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
    
}
