package com.project.helpdesk.application.useCases.ticket;

import com.project.helpdesk.domain.entities.Ticket;
import com.project.helpdesk.domain.pagination.PaginationResult;
import com.project.helpdesk.application.gateways.TicketGateway;

public class ListAllTicketsInteractor {
    
    private final TicketGateway ticketGateway;

    public ListAllTicketsInteractor(TicketGateway ticketGateway) {
        this.ticketGateway = ticketGateway;
    }

    public PaginationResult<Ticket> listAllTickets(Integer currentPage, Integer pageSize) {
        return ticketGateway.listAllTickets(currentPage, pageSize);
    }
}
