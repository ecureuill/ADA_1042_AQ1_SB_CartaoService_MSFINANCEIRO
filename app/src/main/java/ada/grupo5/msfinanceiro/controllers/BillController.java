package ada.grupo5.msfinanceiro.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ada.grupo5.msfinanceiro.dtos.BillDTO;
import ada.grupo5.msfinanceiro.entities.Bill;
import ada.grupo5.msfinanceiro.services.BillService;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/bills")
public class BillController {

    @Autowired
    private BillService billService;

    @GetMapping
    public List<Bill> getAllBills() {
        return billService.findAll();
    }

    @GetMapping("/customer/{cpf}")
    public ResponseEntity<List<Bill>> getBillsByCustomerCpf(@PathVariable String cpf) {
        List<Bill> bills = billService.findBillsByCustomerCpf(cpf);
        return ResponseEntity.ok(bills);
    }

    @GetMapping("/holder/{cpf}")
    public ResponseEntity<List<Bill>> getBillsByHolderCpf(@PathVariable String cpf) {
        List<Bill> bills = billService.findBillsByHolderCpf(cpf);
        return ResponseEntity.ok(bills);
    }

    @PostMapping("/{billId}/pay")
    public ResponseEntity<String> payBill(@PathVariable UUID billId, @RequestParam Double amount) {
        try {
            billService.payBill(billId, amount);
            return new ResponseEntity<>("Pagamento bem sucedido!", HttpStatus.OK);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    public ResponseEntity<BillDTO> getBillForSpecificMonthAndYear(
            @RequestParam String card_number, 
            @RequestParam int month, 
            @RequestParam int year) {
        try {
            BillDTO bill = billService.getBillForSpecificMonthAndYear(card_number, month, year);
            return new ResponseEntity<>(bill, HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
