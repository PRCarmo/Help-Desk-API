package com.project.helpdesk.presentation.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;

import lombok.RequiredArgsConstructor;

import com.project.helpdesk.application.useCases.ticket.*;
import com.project.helpdesk.infrastructure.DTOs.ticket.*;
import com.project.helpdesk.domain.entities.Ticket;
import com.project.helpdesk.domain.enums.TicketStatusEnum;
import com.project.helpdesk.domain.pagination.PaginationResult;

@RestController
@RequestMapping("v1/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final CreateTicketInteractor createTicketInteractor;
    private final DeleteTicketInteractor deleteTicketInteractor;
    private final GetTicketByIdInteractor getTicketByIdInteractor;
    private final ListAllTicketsInteractor listAllTicketsInteractor;
    private final UpdateTicketInteractor updateTicketInteractor;
    private final TicketDTOMapper ticketDTOMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    TicketResponse create(@RequestBody TicketRequest request) {
        Ticket TicketDomainObj = ticketDTOMapper.toTicket(request);
        Ticket ticket = createTicketInteractor.createTicket(TicketDomainObj);

        return ticketDTOMapper.toResponse(ticket);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    TicketResponse getById(@PathVariable Long id) {
        
        Ticket ticket = getTicketByIdInteractor.getTicketById(id);
        
        return ticketDTOMapper.toResponse(ticket);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    PaginationResult<TicketResponse> listAll(
        
        @RequestParam(required = false) Long callerId,
        @RequestParam(required = false) TicketStatusEnum status,
        @RequestParam(required = false) Long assignedToId,
        @RequestParam(required = false, defaultValue = "0") Integer currentPage,
        @RequestParam(required = false, defaultValue = "20") Integer pageSize

    ) {

        PaginationResult<Ticket> paginatedResult = 
            listAllTicketsInteractor.listAllTickets(
                callerId, 
                status, 
                assignedToId, 
                currentPage,
                pageSize
            );

        return  ticketDTOMapper.toPaginatedResponse(paginatedResult);

    }

    @PutMapping("/{id}")
    TicketResponse update(
        @PathVariable Long id, 
        @RequestBody TicketRequest request
        
    ) {

        Ticket ticketDomainObj = ticketDTOMapper.toTicket(request);
        Ticket updatedTicket = updateTicketInteractor.updateTicket(id, ticketDomainObj);

        return ticketDTOMapper.toResponse(updatedTicket);
    }
    
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Long id) {
        
        deleteTicketInteractor.deleteTicket(id);

    }
}
