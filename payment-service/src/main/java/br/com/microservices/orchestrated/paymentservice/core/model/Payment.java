package br.com.microservices.orchestrated.paymentservice.core.model;

import br.com.microservices.orchestrated.paymentservice.core.enums.EPaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Payment.TB_NAME)
@Entity(name = Payment.TB_NAME)
public class Payment {

    protected static final String SEQUENCE_NAME = "validation_sequence";
    protected static final String TB_NAME = "validation";

    @Id
    @SequenceGenerator(name = Payment.SEQUENCE_NAME,
            sequenceName = Payment.SEQUENCE_NAME,
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = Payment.SEQUENCE_NAME )
    private Long id;

    @Column(nullable = false)
    private String orderId;

    @Column(nullable = false)
    private String transactionId;

    @Column(nullable = false)
    private int totalItems;

    @Column(nullable = false)
    private double totalAmount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EPaymentStatus status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    private void prePersist() {
        if (this.createdAt == null) {
            this.status = EPaymentStatus.PENDING;
            this.createdAt = LocalDateTime.now();
            return;
        }
        preUpdate();
    }

    @PreUpdate
    private void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
