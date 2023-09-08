package ada.grupo5.msfinanceiro.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import ada.grupo5.msfinanceiro.dtos.CardType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.Data;

@Data
@Entity
public class Card {
    @Id
    private String id;
    
    @Column(unique = true)
    private String card_number;
    
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "holder_id")
    private Customer holder;
    
    private LocalDate expiration_date;
    private int bill_due_day;
    private String cvv;
    private String password;
    private CardType type;
    private String bank_account_id;
    private boolean active;
    private LocalDateTime created_at;
    
    @PrePersist
    protected void onCreate() {
        this.created_at = LocalDateTime.now();
    }
}
