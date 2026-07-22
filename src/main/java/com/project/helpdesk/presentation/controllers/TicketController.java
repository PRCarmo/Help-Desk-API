package com.project.helpdesk.presentation.controllers;

import com.project.helpdesk.infrastructure.persistence.ticket.TicketRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import lombok.RequiredArgsConstructor;

import com.project.helpdesk.application.useCases.ticket.*;
import com.project.helpdesk.infrastructure.DTOs.ticket.*;
import com.project.helpdesk.presentation.TicketSpecification;
import com.project.helpdesk.domain.entities.Ticket;
import com.project.helpdesk.domain.entities.User;
import com.project.helpdesk.domain.enums.TicketStatusEnum;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("v1/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketRepository ticketRepository;
    private final CreateTicketInteractor createTicketInteractor;
    private final DeleteTicketInteractor deleteTicketInteractor;
    private final GetTicketByIdInteractor getTicketByIdInteractor;
    private final ListAllTicketsInteractor listAllTicketsInteractor;
    private final UpdateTicketInteractor updateTicketInteractor;
    private final TicketDTOMapper ticketDTOMapper;

    @PostMapping
    TicketResponse create(@RequestBody TicketRequest request) {
        Ticket TicketBussinessObj = ticketDTOMapper.toTicket(request);
        Ticket ticket = createTicketInteractor.createTicket(TicketBussinessObj);

        return ticketDTOMapper.toResponse(ticket);
    }

    @GetMapping("/{id}")
    TicketResponse getById(@PathVariable Long id) {
        
        Ticket ticket = getTicketByIdInteractor.getTicketById(id);
        
        return ticketDTOMapper.toResponse(ticket);
    }

    @GetMapping
    Page<TicketResponse> listAll(
        
        @RequestParam(required = false) User caller,
        @RequestParam(required = false) TicketStatusEnum status,
        @RequestParam(required = false) User assignedTo,
        Pageable pageable

    ) {
        
        Specification<Ticket> spec = TicketSpecification.withFilters(caller, status, assignedTo);
        return ticketRepository.findAll(spec, pageable).map(ticketDTOMapper::toResponse);

    }

    @PutMapping("/{id}")
    TicketResponse update(
        @PathVariable Long id, 
        @RequestBody Ticket ticket
        
    ) {

        Ticket updatedTicket = updateTicketInteractor.updateTicket(id, ticket);

        return ticketDTOMapper.toResponse(updatedTicket);
    }
    
    @DeleteMapping
    void delete(@PathVariable Long id) {
        
        deleteTicketInteractor.deleteTicket(id);

    }
}
