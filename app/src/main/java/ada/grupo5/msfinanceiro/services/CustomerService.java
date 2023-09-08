package ada.grupo5.msfinanceiro.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import ada.grupo5.msfinanceiro.dtos.CreateCardDependent;
import ada.grupo5.msfinanceiro.dtos.CreateCardUser;
import ada.grupo5.msfinanceiro.entities.Address;
import ada.grupo5.msfinanceiro.entities.Customer;
import ada.grupo5.msfinanceiro.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Optional<Customer> findByCpf(String cpf) {
        return customerRepository.findByCpf(cpf);
    }

    public Customer createCustomer(CreateCardUser creatCardRequest) {
        Address address = createAddress(creatCardRequest);
        Customer customer = new Customer();
        customer.setCpf(creatCardRequest.cpf());
        customer.setName(creatCardRequest.name());
        customer.setAddress(address);
        customer.setDependent(false);
        customer.setHolder(null);

        return customerRepository.save(customer);

    }

    public Customer createDependent(CreateCardDependent createCardRequest, Customer customer) {
        Customer dependent = new Customer();
        dependent.setCpf(createCardRequest.cpf());
        dependent.setName(createCardRequest.name());
        dependent.setAddress(customer.getAddress());
        dependent.setDependent(true);
        dependent.setHolder(customer);

        return customerRepository.save(customer);

    }

    private Address createAddress(CreateCardUser dto) {
        Address address = new Address(
            dto.address().zipCode(),
            dto.address().street(),
            dto.address().neighborhood(),
            dto.address().city(),
            dto.address().state(),
            dto.address().country(),
            dto.address().number(),
            dto.address().complement()
        );
        return address;
    }


}
