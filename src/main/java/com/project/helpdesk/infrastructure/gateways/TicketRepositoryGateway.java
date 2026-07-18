package com.project.helpdesk.infrastructure.gateways;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public void deleteTicket(Long id) {
        TicketEntity user = 
            ticketRepository.findById(id)
                .orElseThrow(() -> 
                    new ResourceNotFoundException("Ticket with ID " + id + " not found")
            );
    }
    
    @Override
    public Ticket getTicketById(Long id) {
        TicketEntity foundTicket =
            ticketRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException("Ticket with ID " + id + " not found")
            );
    }

    @Override
    public List<Ticket> listAllTickets() {
        List<TicketEntity> ticketEntities = ticketRepository.findAll();
        List<Ticket> tickets =
            ticketEntities.stream()
                .map(ticketEntityMapper::toDomainObj)
                    .collect(Collectors.toList());
    }

    @Override
    public Ticket updateTicket(Long id, Ticket ticket) {
        TicketEntity requestedTicket = 
            ticketRepository.findById(id)
                .orElseThrow(()->
                    ResourceNotFoundException("User with ID " + id + " not found")
            );

        TicketEntity updateInfo = 
            ticketEntityMapper.toEntity(ticket);

        requestedTicket.setCaller(updateInfo.getCaller());
        requestedTicket.setProblem(updateInfo.getProblem());
        requestedTicket.setDescription(updateInfo.getDescription());
        requestedTicket.setStatus(updateInfo.getStatus());
        requestedTicket.setAssignedTo(updateInfo.getAssignedTo());

        ticketRepository.save(requestedTicket);

        Ticket updatedTicket = ticketEntityMapper.toDomainObj(requestedTicket);

        return updatedTicket;
    }
}
