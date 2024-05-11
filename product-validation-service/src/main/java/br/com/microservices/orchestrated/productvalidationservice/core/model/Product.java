package br.com.microservices.orchestrated.productvalidationservice.core.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Product.TB_NAME)
@Entity(name = Product.TB_NAME)
public class Product {
    protected static final String SEQUENCE_NAME = "product_sequence";
    protected static final String TB_NAME = "product";

    @Id
    @SequenceGenerator(name = Product.SEQUENCE_NAME,
            sequenceName = Product.SEQUENCE_NAME,
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = Product.SEQUENCE_NAME )
    private Long id;

    @Column(nullable = false)
    private String code;
}
