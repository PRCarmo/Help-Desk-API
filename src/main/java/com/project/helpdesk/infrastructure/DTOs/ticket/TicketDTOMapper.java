package com.project.helpdesk.infrastructure.DTOs.ticket;

import java.util.List;

import com.project.helpdesk.domain.entities.Ticket;
import com.project.helpdesk.infrastructure.DTOs.ticket.TicketResponse;
import com.project.helpdesk.infrastructure.DTOs.ticket.TicketRequest;
import com.project.helpdesk.domain.pagination.PaginationResult;

public class TicketDTOMapper {

    public TicketResponse toResponse(Ticket ticket) {
        return new TicketResponse(
            ticket.caller(),
            ticket.problem(),
            ticket.description(),
            ticket.status(),
            ticket.createdAt(),
            ticket.solvedAt(),
            ticket.assignedTo()
        );
    }

    public Ticket toTicket(TicketRequest request) {
        return new Ticket(
            request.caller(),
            request.problem(),
            request.description(),
            request.status(),
            request.createdAt(),
            request.solvedAt(),
            request.assignedTo()
        );
    }

    public PaginationResult<TicketResponse> toPaginatedResponse(PaginationResult<Ticket> paginatedObj) {

        List<TicketResponse> tickets = 
            paginatedObj
                .data()
                .stream()
                .map(this::toResponse)
                .toList();

        return new PaginationResult<>(
            tickets,
            paginatedObj.currentPage(),
            paginatedObj.pageSize(),
            paginatedObj.totalElements(),
            paginatedObj.totalPages()
        );
    }
}
