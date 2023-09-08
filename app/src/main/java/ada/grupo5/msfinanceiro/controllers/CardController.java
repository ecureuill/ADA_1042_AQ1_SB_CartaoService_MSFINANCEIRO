package ada.grupo5.msfinanceiro.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ada.grupo5.msfinanceiro.dtos.CardDTO;
import ada.grupo5.msfinanceiro.dtos.CardResponse;
import ada.grupo5.msfinanceiro.dtos.CardValidationResponse;
import ada.grupo5.msfinanceiro.dtos.CreateCardDependent;
import ada.grupo5.msfinanceiro.dtos.CreateCardUser;
import ada.grupo5.msfinanceiro.entities.Card;
import ada.grupo5.msfinanceiro.exceptions.CardNotFoundException;
import ada.grupo5.msfinanceiro.exceptions.CustomerAlreadyHaveActiveCardException;
import ada.grupo5.msfinanceiro.exceptions.CustomerHasNoActiveCardException;
import ada.grupo5.msfinanceiro.exceptions.CustomerNotFoundException;
import ada.grupo5.msfinanceiro.services.CardService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @GetMapping
    public ResponseEntity<String> ping(){
        return ResponseEntity.ok().body("up");
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CardResponse> createMainCard(@RequestBody @Valid CreateCardUser dto) throws CustomerAlreadyHaveActiveCardException{
        Card card = cardService.createMainCard(dto);
        return ResponseEntity.ok().body(new CardResponse(card));
    }
    
    @PostMapping("/additional")
    @Transactional
    public ResponseEntity<CardResponse> createAdditionalCard(@RequestBody @Valid CreateCardDependent dto) throws CustomerAlreadyHaveActiveCardException, CustomerNotFoundException, CustomerHasNoActiveCardException{
        Card card = cardService.createAdditionalCard(dto);
        return ResponseEntity.ok().body(new CardResponse(card));
    }

    @Transactional
    @DeleteMapping("/{cardNumber}")
    public ResponseEntity<Void> deleteCard(@PathVariable String cardNumber) throws CardNotFoundException {
        cardService.deleteCard(cardNumber);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/validate")
    public ResponseEntity<CardValidationResponse> getCard(@RequestBody @Valid CardDTO dto) throws CardNotFoundException {
        return ResponseEntity.ok().body(new CardValidationResponse(cardService.verify(dto)));
    }

    
}
