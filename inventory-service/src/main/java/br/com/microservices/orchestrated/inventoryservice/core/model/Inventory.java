package br.com.microservices.orchestrated.inventoryservice.core.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Inventory.TB_NAME)
@Entity(name = Inventory.TB_NAME)
public class Inventory {

    protected static final String SEQUENCE_NAME = "inventory_sequence";
    protected static final String TB_NAME = "inventory";

    @Id
    @SequenceGenerator(name = Inventory.SEQUENCE_NAME,
            sequenceName = Inventory.SEQUENCE_NAME,
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = Inventory.SEQUENCE_NAME )
    private Long id;

    @Column(nullable = false)
    private String productCode;

    @Column(nullable = false)
    private Integer available;
}
