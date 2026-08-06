package com.project.helpdesk.infrastructure.DTOs.ticket;

import java.util.List;

import com.project.helpdesk.domain.entities.Ticket;
import com.project.helpdesk.infrastructure.DTOs.ticket.TicketResponse;
import com.project.helpdesk.infrastructure.DTOs.ticket.TicketRequest;
import com.project.helpdesk.domain.pagination.PaginatedResult;

public class TicketDTOMapper {

    public TicketResponse toResponse(Ticket ticket) {
        return new TicketResponse(
            ticket.callerId(),
            ticket.problem(),
            ticket.description(),
            ticket.status(),
            ticket.createdAt(),
            ticket.updatedAt(),
            ticket.assignedToId()
        );
    }

    public Ticket toTicket(TicketRequest request) {
        return new Ticket(
            request.callerId(),
            request.problem(),
            request.description(),
            request.status(),
            request.createdAt(),
            request.updatedAt(),
            request.assignedToId()
        );
    }

    public PaginatedResult<TicketResponse> toPaginatedResponse(PaginatedResult<Ticket> paginatedObj) {

        List<TicketResponse> tickets = 
            paginatedObj
                .data()
                .stream()
                .map(this::toResponse)
                .toList();

        return new PaginatedResult<>(
            tickets,
            paginatedObj.currentPage(),
            paginatedObj.pageSize(),
            paginatedObj.totalElements(),
            paginatedObj.totalPages()
        );
    }
}
