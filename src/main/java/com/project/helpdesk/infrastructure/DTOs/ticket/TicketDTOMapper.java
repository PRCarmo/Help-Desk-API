package com.project.helpdesk.infrastructure.DTOs.ticket;

import com.project.helpdesk.domain.entities.Ticket;

public class TicketDTOMapper {
    public TicketResponse toResponse(Ticket ticket) {
        return new TicketResponse(
            ticket.callerId(),
            ticket.problem(),
            ticket.description(),
            ticket.status(),
            ticket.createdAt(),
            ticket.solvedAt(),
            ticket.assignedToId()
        );
    }

    public Ticket toTicket(TicketRequest request) {
        return new Ticket(
            request.callerId(),
            request.problem(),
            request.description(),
            request.status(),
            request.createdAt(),
            request.solvedAt(),
            request.assignedToId()
        );
    }
}
