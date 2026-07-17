package com.project.helpdesk.application.useCases;

import com.project.helpdesk.domain.entities.Ticket;
import com.project.helpdesk.application.gateways.TicketGateway;

public class CreateTicketInteractor {

    private final TicketGateway ticketGateway;

    public CreateTicketInteractor(TicketGateway ticketGateway) {
        this.ticketGateway = ticketGateway;
    }

    public Ticket createTicket(Ticket ticket) {
        return ticketGateway.createTicket(ticket);
    }
    
}
