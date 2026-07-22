package com.project.helpdesk.presentation;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import com.project.helpdesk.domain.entities.Ticket;
import com.project.helpdesk.domain.entities.User;
import com.project.helpdesk.domain.enums.TicketStatusEnum;

import java.util.List;
import java.util.ArrayList;

public class TicketSpecification {
    
    public static Specification<Ticket> withFilters(
            User caller,
            TicketStatusEnum status,
            User assignedTo
    ) {
        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (caller != null) {
                predicates.add(
                    cb.equal(root.get("caller"), caller)
                );
            }

            if (status != null) {
                predicates.add(
                    cb.equal(root.get("status"), status)
                );
            }

            if (assignedTo != null) {
                predicates.add(
                    cb.equal(root.get("assignedTo"), assignedTo)
                );
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
