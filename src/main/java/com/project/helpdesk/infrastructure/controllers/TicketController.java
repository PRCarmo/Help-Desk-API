package com.project.helpdesk.infrastructure.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;

import lombok.RequiredArgsConstructor;

import com.project.helpdesk.application.useCases.*;
import com.project.helpdesk.infrastructure.controllers.DTOs.CreateTicketRequest;
import com.project.helpdesk.infrastructure.controllers.DTOs.CreateTicketResponse;
import com.project.helpdesk.infrastructure.controllers.DTOs.TicketDTOMapper;
import com.project.helpdesk.domain.entities.Ticket;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("v1/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final CreateTicketInteractor createTicketInteractor;
    private final DeleteTicketInteractor deleteTicketInteractor;
    private final GetTicketByIdInteractor getTicketByIdInteractor;
    private final TicketDTOMapper ticketDTOMapper;

    @PostMapping
    CreateTicketResponse create(@RequestBody CreateTicketRequest request) {
        Ticket TicketBussinessObj = ticketDTOMapper.toTicket(request);
        Ticket ticket = createTicketInteractor.createTicket(TicketBussinessObj);

        return ticketDTOMapper.toResponse(ticket);
    }

    @GetMapping("/{id}")
    ResponseEntity<Ticket> getById(@PathVariable Long id) {
        return ResponseEntity.ok(
            getTicketByIdInteractor
                .getTicketById(id)
        );
    }
    
    @DeleteMapping
    ResponseEntity<Void> delete(@PathVariable Long id) {
        
        deleteTicketInteractor.deleteTicket(id);

        return ResponseEntity.noContent().build();
    }
}
