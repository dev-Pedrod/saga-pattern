package br.com.microservices.orchestrated.orderservice.core.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Document(collection = "order")
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    private String id;
    private LocalDateTime createdAt;
    private String transactionId;
    private Double totalAmount;
    private Integer totalItems;
    private List<OrderProduct> products = new ArrayList<>();

}
