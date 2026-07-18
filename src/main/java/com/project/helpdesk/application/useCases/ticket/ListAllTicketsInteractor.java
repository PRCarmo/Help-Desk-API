package com.project.helpdesk.application.useCases.ticket;

import com.project.helpdesk.domain.entities.Ticket;
import com.project.helpdesk.application.gateways.TicketGateway;
import java.util.List;

public class ListAllTicketsInteractor {
    
    private final TicketGateway ticketGateway;

    public ListAllTicketsInteractor(TicketGateway ticketGateway) {
        this.ticketGateway = ticketGateway;
    }

    public List<Ticket> listAllTickets() {
        return ticketGateway.listAllTickets();
    }
}
