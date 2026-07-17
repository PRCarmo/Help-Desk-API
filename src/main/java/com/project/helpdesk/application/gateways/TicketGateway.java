package com.project.helpdesk.application.gateways;

import com.project.helpdesk.domain.entities.Ticket;

public interface TicketGateway {
    Ticket createTicket(Ticket ticket);

    void deleteTicket(Long id);

    Ticket getTicketById(Long id);
}
