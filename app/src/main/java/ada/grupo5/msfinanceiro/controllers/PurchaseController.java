package ada.grupo5.msfinanceiro.controllers;

import ada.grupo5.msfinanceiro.dtos.PurchaseDTO;
import ada.grupo5.msfinanceiro.dtos.PurchaseResponseDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import jakarta.transaction.Transactional;

import ada.grupo5.msfinanceiro.services.PurchaseService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/purchase")
@RequiredArgsConstructor
public class PurchaseController {
    
    private final PurchaseService service;
        @PostMapping
        @Transactional
        public ResponseEntity<PurchaseResponseDTO> createPurchase(@RequestBody @Valid PurchaseDTO dto) throws Exception {
            var purchase = service.registerPayment(dto);
            
            return ResponseEntity.ok().body(new PurchaseResponseDTO(purchase));
        
        }
}
