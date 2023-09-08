package ada.grupo5.msfinanceiro.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ada.grupo5.msfinanceiro.entities.Bill;
import ada.grupo5.msfinanceiro.services.BillService;

@RestController
@RequestMapping("/bills")
public class BillController {

    @Autowired
    private BillService billService;

    @GetMapping
    public List<Bill> getAllBills() {
        return billService.findAll();
    }

/*     @GetMapping("/holder/{holderId}")
    public List<Bill> getBillsByHolderId(@PathVariable Long holderId) {
        return billService.getBillsByHolderId(holderId);
    }

    @GetMapping("/customer/{customerId}")
    public List<Bill> getBillsByCustomerId(@PathVariable Long customerId) {
        return billService.getBillsByCustomerId(customerId);
    } */
}
