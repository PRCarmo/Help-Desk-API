package com.project.helpdesk.application.useCases.ticket;

import com.project.helpdesk.domain.entities.Ticket;
import com.project.helpdesk.application.gateways.TicketGateway;

public class UpdateTicketInteractor {
    
    private final TicketGateway ticketGateway;

    public UpdateTicketInteractor(TicketGateway ticketGateway) {
        this.ticketGateway = ticketGateway;
    }

    public Ticket updateTicket(Long id, Ticket ticket) {
        return ticketGateway.updateTicket(id, ticket);
    }
}
