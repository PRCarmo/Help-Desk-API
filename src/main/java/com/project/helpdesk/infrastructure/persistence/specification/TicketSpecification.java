package com.project.helpdesk.infrastructure.persistence.specification;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import com.project.helpdesk.domain.enums.TicketStatusEnum;
import com.project.helpdesk.infrastructure.persistence.ticket.TicketEntity;

import java.util.List;
import java.util.ArrayList;

public class TicketSpecification {
    
    public static Specification<TicketEntity> withFilters(
            Long callerId,
            TicketStatusEnum status,
            Long assignedToId
    ) {
        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (callerId != null) {
                predicates.add(
                    cb.equal(root.get("callerId"), callerId)
                );
            }

            if (status != null) {
                predicates.add(
                    cb.equal(root.get("status"), status)
                );
            }

            if (assignedToId != null) {
                predicates.add(
                    cb.equal(root.get("assignedToId"), assignedToId)
                );
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
