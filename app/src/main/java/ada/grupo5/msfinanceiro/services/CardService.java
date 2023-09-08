package ada.grupo5.msfinanceiro.services;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import ada.grupo5.msfinanceiro.dtos.CardDTO;
import ada.grupo5.msfinanceiro.dtos.CardType;
import ada.grupo5.msfinanceiro.entities.Card;
import ada.grupo5.msfinanceiro.entities.Customer;
import ada.grupo5.msfinanceiro.repositories.CardRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final CustomerService customerService;

    public Card createMainCard(CreateCardUser dto) throws CustomerAlreadyHaveActiveCardException {
        
        Optional<Customer> existCustomer = customerService.findByCpf(dto.cpf());
        if(existCustomer.isPresent())
        {
            Customer customer = existCustomer.get();
            Optional<Card> activeCard = getCustomerActiveCard(customer.getId());

            if(activeCard.isEmpty()){
                Card card = createCard(customer, dto.creditCardType());
                return card;
            }
            else if(activeCard.get().getExpiration_date().isBefore(LocalDate.now()))
            {
                activeCard.get().setActive(false);
                cardRepository.save(activeCard.get());

                Card card = createCard(customer, dto.creditCardType());
                return card;
            }

            throw new CustomerAlreadyHaveActiveCardException();
        
        }

        Customer customer = customerService.createCustomer(dto);
        Card card = createCard(customer, dto.creditCardType());
        return card;
    }

    public Card createAdditionalCard(CreateCardDependent dto) throws CustomerNotFoundException, CustomerAlreadyHaveActiveCardException, CustomerHasNoActiveCardException {

        Optional<Customer> existCustomer = customerService.findByCpf(dto.customerCPF());
        if(existCustomer.isEmpty()){
            throw new CustomerNotFoundException(dto.customerCPF());
        }
        Optional<Card> activeCustomerCard = getCustomerActiveCard(existCustomer.get().getId());
        if(activeCustomerCard.isEmpty()){
            throw new CustomerHasNoActiveCardException();
        }

        Optional<Customer> existDependent = customerService.findByCpf(dto.cpf());
        if(existDependent.isPresent()){
            Customer dependent = existCustomer.get();
            Optional<Card> activeCard = getCustomerActiveCard(dependent.getId());

            if(activeCard.isEmpty()){
                Card card = createCard(dependent, activeCustomerCard.get().getType());
                return card;
            }
            else if(activeCard.get().getExpiration_date().isBefore(LocalDate.now()))
            {
                activeCard.get().setActive(false);
                cardRepository.save(activeCard.get());

                Card card = createCard(dependent, activeCustomerCard.get().getType());
                return card;
            }

            throw new CustomerAlreadyHaveActiveCardException();
        }
        else
        {
            Customer dependent = customerService.createDependent(dto, existCustomer.get());
            Card card = createCard(dependent, activeCustomerCard.get().getType());
            return card;
        }

    }

    private Card createCard(Customer customer, CardType type) {
        Card card = new Card();
        card.setCard_number(generateCardNumber());
        card.setExpiration_date(LocalDate.now().plusYears(5));
        card.setActive(true);
        card.setCustomer(customer);
        card.setBank_account_id("256");
        card.setBill_due_day(21);
        card.setCvv(generateCVV());
        card.setPassword(generatePIN());
        card.setType(type);
        return cardRepository.save(card);
    }

    private Optional<Card> getCustomerActiveCard(Long id) {
        return cardRepository.findByIdAndActiveTrue(id);
    }

    private String generateCardNumber(){
        Random random = new Random();
        return "" + random.nextLong(5200000000000000L, 5299999999999999L);
    }

    private String generateCVV(){
        Random random = new Random();
        return "" + random.nextInt(100, 999);
    }

    private String generatePIN(){
        Random random = new Random();
        return "" + random.nextInt(1000, 9999);
    }

    public void deleteCard(String cardNumber) throws CardNotFoundException {
        Optional<Card> card = cardRepository.findByCard_number(cardNumber);
        if(card.isPresent())
        {
            card.get().setActive(false);
            cardRepository.save(card.get());
        }
        else
        {
            throw new CardNotFoundException(cardNumber);
        }
        
    }

    public Boolean verify(CardDTO dto) {
        Optional<Card> existCard = cardRepository.findByCard_number(dto.cardNumber());
        if(existCard.isEmpty())
            return false;
        
        Card card = existCard.get();
        if(card.getExpiration_date().isBefore(LocalDate.now()))
        {
            return false;
        }
        
        if(!card.isActive())
        {
            return false;
        }

        return  card.getCvv().equals(dto.cvv()) && 
                card.getExpiration_date().equals(dto.expireCardDate()) &&
                card.getCustomerName().equals(dto.accountOwner());
    
        
    }
}
