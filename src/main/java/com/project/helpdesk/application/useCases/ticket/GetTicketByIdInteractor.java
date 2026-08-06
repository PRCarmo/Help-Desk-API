package com.project.helpdesk.application.useCases.ticket;

import com.project.helpdesk.application.gateways.TicketGateway;
import com.project.helpdesk.domain.entities.Ticket;

public class GetTicketByIdInteractor {
    
    private final TicketGateway ticketGateway;

    public GetTicketByIdInteractor(TicketGateway ticketGateway) {
        this.ticketGateway = ticketGateway;
    }

    public Ticket getTicketById(Long id) {
        return ticketGateway.getTicketById(id);
    }
}
