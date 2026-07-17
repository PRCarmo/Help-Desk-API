package com.project.helpdesk.application.useCases;

import com.project.helpdesk.application.gateways.TicketGateway;

public class DeleteTicketInteractor {
    
    private final TicketGateway ticketGateway;

    public DeleteTicketInteractor(TicketGateway ticketGateway) {
        this.ticketGateway = ticketGateway;
    }

    public void deleteTicket(Long id) {
        ticketGateway.deleteTicket(id);
    }

}
