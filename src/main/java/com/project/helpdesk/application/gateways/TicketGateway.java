package com.project.helpdesk.application.gateways;

import com.project.helpdesk.domain.entities.Ticket;
import com.project.helpdesk.domain.enums.TicketStatusEnum;
import com.project.helpdesk.domain.pagination.PaginationResult;

public interface TicketGateway {
    Ticket createTicket(Ticket ticket);

    void deleteTicket(Long id);

    Ticket getTicketById(Long id);

    PaginationResult<Ticket> listAllTickets(
        Long callerId,
        TicketStatusEnum status,
        Long assignedToId,
        Integer currentPage, 
        Integer pageSize
    );

    Ticket updateTicket(Long id, Ticket ticket);
}
