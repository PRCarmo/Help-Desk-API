package com.project.helpdesk.infrastructure.gateways;

import com.project.helpdesk.application.gateways.TicketGateway;
import com.project.helpdesk.domain.entities.Ticket;
import com.project.helpdesk.infrastructure.persistence.TicketEntity;
import com.project.helpdesk.infrastructure.persistence.TicketRepository;
import com.project.helpdesk.infrastructure.gateways.TicketEntityMapper;

public class TicketRepositoryGateway implements TicketGateway {

    private final TicketRepository ticketRepository;
    private final TicketEntityMapper ticketEntityMapper;

    public TicketRepositoryGateway(
        TicketRepository ticketRepository, 
        TicketEntityMapper ticketEntityMapper
    ) {
        this.ticketRepository = ticketRepository;
        this.ticketEntityMapper = ticketEntityMapper;
    }

    @Override
    public Ticket createTicket(Ticket ticketDomainObj) {
        TicketEntity ticketEntity = ticketEntityMapper.toEntity(ticketDomainObj);
        TicketEntity savedObj = ticketRepository.save(ticketEntity);
        return ticketEntityMapper.toDomainObj(savedObj);
    }
    
}
