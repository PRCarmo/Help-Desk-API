package com.project.helpdesk.infrastructure.gateways.ticket;

import com.project.helpdesk.domain.entities.Ticket;
import com.project.helpdesk.infrastructure.persistence.ticket.TicketEntity;

public class TicketEntityMapper {

    TicketEntity toEntity(Ticket ticketDomainObj) {
        return new TicketEntity(
            ticketDomainObj.callerId(),
            ticketDomainObj.problem(),
            ticketDomainObj.description(),
            ticketDomainObj.status(),
            ticketDomainObj.createdAt(),
            ticketDomainObj.updatedAt(),
            ticketDomainObj.assignedToId()
        );
    }

    Ticket toDomainObj(TicketEntity ticketEntity) {
        return new Ticket(
            ticketEntity.getCallerId(),
            ticketEntity.getProblem(),
            ticketEntity.getDescription(),
            ticketEntity.getStatus(),
            ticketEntity.getCreatedAt(),
            ticketEntity.getUpdatedAt(),
            ticketEntity.getAssignedToId()
        );
    }

}
