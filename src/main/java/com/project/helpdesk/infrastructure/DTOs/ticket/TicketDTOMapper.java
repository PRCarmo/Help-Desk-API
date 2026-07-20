package com.project.helpdesk.infrastructure.DTOs.ticket;

import com.project.helpdesk.domain.entities.Ticket;

public class TicketDTOMapper {
    public TicketResponse toResponse(Ticket ticket) {
        return new TicketResponse(
            ticket.caller(),
            ticket.problem(),
            ticket.description(),
            ticket.status(),
            ticket.createdAt(),
            ticket.solvedAt(),
            ticket.assignedTo()
        );
    }

    public Ticket toTicket(TicketRequest request) {
        return new Ticket(
            request.caller(),
            request.problem(),
            request.description(),
            request.status(),
            request.createdAt(),
            request.solvedAt(),
            request.assignedTo()
        );
    }
}
