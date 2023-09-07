package ada.grupo5.msfinanceiro.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String cpf;
    private Boolean dependent;

    @ManyToOne
    @JoinColumn(name = "holder_id")
    private Customer holder;

    private LocalDateTime created_at;

    @Embedded
    private Address address;

    @PrePersist
    protected void onCreate() {
        this.created_at = LocalDateTime.now();
    }

    @Transient
    public Long getHolderId() {
        return holder != null ? holder.getId() : null;
    }

    public void setDependent(Boolean dependent) {
        this.dependent = dependent;
        if (!dependent) {
            this.holder = this;
        }
    }
}
