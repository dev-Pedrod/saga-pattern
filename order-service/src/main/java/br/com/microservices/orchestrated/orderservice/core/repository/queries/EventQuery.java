package br.com.microservices.orchestrated.orderservice.core.repository.queries;

import br.com.microservices.orchestrated.orderservice.core.dto.EventFilters;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@NoArgsConstructor
public class EventQuery {

    private static final String ORDER_ID = "orderId";
    private static final String TRANSACTION_ID = "transactionId";
    private static final String CREATED_AT = "createdAt";


    public Query filter(EventFilters filters) {
        Query query = new Query().with(Sort.by(Sort.Direction.DESC, CREATED_AT));

        Optional.ofNullable(filters.getTransactionId())
                .ifPresent(transactionID -> query.addCriteria(Criteria.where(TRANSACTION_ID).is(transactionID)));

        Optional.ofNullable(filters.getTransactionId())
                .ifPresent(orderID -> query.addCriteria(Criteria.where(ORDER_ID).is(orderID)));

        return query;
    }
}
