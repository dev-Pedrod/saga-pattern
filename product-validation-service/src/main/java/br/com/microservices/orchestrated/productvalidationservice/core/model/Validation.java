package br.com.microservices.orchestrated.productvalidationservice.core.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Validation.TB_NAME)
@Entity(name = Validation.TB_NAME)
public class Validation {
    protected static final String SEQUENCE_NAME = "validation_sequence";
    protected static final String TB_NAME = "validation";

    @Id
    @SequenceGenerator(name = Validation.SEQUENCE_NAME,
            sequenceName = Validation.SEQUENCE_NAME,
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = Validation.SEQUENCE_NAME )
    private Long id;

    @Column(nullable = false)
    private String transactionId;

    @Column(nullable = false)
    private String orderId;

    @Column(nullable = false)
    private Boolean success;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    private void prePersist() {
        if (this.createdAt == null) {
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
