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
@Document(collection = "event")
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    private String id;
    private String transactionId;
    private String orderId;
    private Order payload;
    private String source;
    private LocalDateTime createdAt;
    private String status;
    private List<History> eventHistory = new ArrayList<>();
}
