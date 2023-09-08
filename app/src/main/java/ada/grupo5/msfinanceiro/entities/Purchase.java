package ada.grupo5.msfinanceiro.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import ada.grupo5.msfinanceiro.dtos.PurchaseStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.Data;

@Data
@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    private LocalDateTime date;
    private Double value;

    @ManyToOne
    @JoinColumn(name = "card_number", referencedColumnName = "card_number", insertable = false, updatable = false)
    private Card card;

    private PurchaseStatus status;
    private Boolean processed = false;
    private String store;
    
    @PrePersist
    protected void onCreate() {
        this.date = LocalDateTime.now();
    }

}
