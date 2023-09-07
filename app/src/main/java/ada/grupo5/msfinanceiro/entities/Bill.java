package ada.grupo5.msfinanceiro.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

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
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDate due_date;
    private LocalDate processing_date;
    private Double value;
    private Double paid_value;

    @ManyToOne
    @JoinColumn(name = "card_number", referencedColumnName = "card_number", insertable = false, updatable = false)
    private Card card;

    private String card_number;
    
    private LocalDateTime created_at;
    
    @PrePersist
    protected void onCreate() {
        this.created_at = LocalDateTime.now();
    }
}

