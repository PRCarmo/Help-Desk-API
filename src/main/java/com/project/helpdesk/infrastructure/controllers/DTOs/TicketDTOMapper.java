package com.project.helpdesk.infrastructure.controllers.DTOs;

import com.project.helpdesk.domain.entities.Ticket;

public class TicketDTOMapper {
    public CreateTicketResponse toResponse(Ticket ticket) {
        return new CreateTicketResponse(
            ticket.caller(),
            ticket.problem(),
            ticket.description(),
            ticket.status(),
            ticket.createdAt(),
            ticket.solvedAt()
        );
    }

    public Ticket toTicket(CreateTicketRequest request) {
        return new Ticket(
            request.caller(),
            request.problem(),
            request.description(),
            request.status(),
            request.createdAt(),
            request.solvedAt()
        );
    }
}
