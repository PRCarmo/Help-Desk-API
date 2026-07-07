package com.project.helpdesk.infrastructure.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.RequiredArgsConstructor;

import com.project.helpdesk.application.useCases.CreateTicketInteractor;
import com.project.helpdesk.infrastructure.controllers.DTOs.CreateTicketRequest;
import com.project.helpdesk.infrastructure.controllers.DTOs.CreateTicketResponse;
import com.project.helpdesk.infrastructure.controllers.DTOs.TicketDTOMapper;
import com.project.helpdesk.domain.entities.Ticket;

@RestController
@RequestMapping("v1/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final CreateTicketInteractor createTicketInteractor;
    private final TicketDTOMapper ticketDTOMapper;

    @PostMapping
    CreateTicketResponse create(@RequestBody CreateTicketRequest request) {
        Ticket TicketBussinessObj = ticketDTOMapper.toTicket(request);
        Ticket ticket = createTicketInteractor.createTicket(TicketBussinessObj);

        return ticketDTOMapper.toResponse(ticket);
    }
    
    
}
