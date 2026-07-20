package com.project.helpdesk.presentation.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;

import java.util.List;

import com.project.helpdesk.application.useCases.ticket.*;
import com.project.helpdesk.infrastructure.DTOs.ticket.*;
import com.project.helpdesk.domain.entities.Ticket;
import com.project.helpdesk.infrastructure.DTOs.ticket.TicketDTOMapper;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;



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
    List<Ticket> listAll() {
        
        return listAllTicketsInteractor.listAllTickets();

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
