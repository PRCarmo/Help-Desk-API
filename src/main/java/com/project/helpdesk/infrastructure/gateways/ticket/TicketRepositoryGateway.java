package com.project.helpdesk.infrastructure.gateways.ticket;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import com.project.helpdesk.application.gateways.TicketGateway;
import com.project.helpdesk.domain.entities.Ticket;
import com.project.helpdesk.domain.pagination.PaginationResult;
import com.project.helpdesk.infrastructure.persistence.ticket.TicketEntity;
import com.project.helpdesk.infrastructure.persistence.ticket.TicketRepository;
import com.project.helpdesk.infrastructure.persistence.user.UserEntity;

public class TicketRepositoryGateway implements TicketGateway {

    private final TicketRepository repository;
    private final TicketEntityMapper entityMapper;

    public TicketRepositoryGateway(
        TicketRepository repository, 
        TicketEntityMapper entityMapper
    ) {
        this.repository = repository;
        this.entityMapper = entityMapper;
    }

    @Override
    public Ticket createTicket(Ticket ticketDomainObj) {
        TicketEntity ticket = entityMapper.toEntity(ticketDomainObj);

        Long ticketId = ticket.getId();

        if (repository.existsById(ticketId)) {
            throw new ResourceNotFoundException("Ticket with id " + ticketId + " already exists");
        } else {
            TicketEntity savedObj = repository.save(ticket);
            return entityMapper.toDomainObj(savedObj);
        }
    }

    @Override
    public void deleteTicket(Long id) {
        TicketEntity user = 
            repository.findById(id)
                .orElseThrow(() -> 
                    new ResourceNotFoundException("Ticket with ID " + id + " not found")
            );

        repository.delete(user);
    }
    
    @Override
    public Ticket getTicketById(Long id) {
        TicketEntity foundTicket =
            repository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException("Ticket with ID " + id + " not found")
            );

        return entityMapper.toDomainObj(foundTicket);
    }

    @Override
    public PaginationResult<Ticket> listAllTickets(Integer currentPage, Integer pageSize) {
        
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Page<TicketEntity> page = repository.findAll(pageable);

        List<Ticket> tickets = entityMapper.toDomainList(page.getContent());

        return new PaginationResult<>(
                tickets,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }

    @Override
    public Ticket updateTicket(Long id, Ticket ticket) {
        TicketEntity requestedTicket = 
            repository.findById(id)
                .orElseThrow(()->
                    ResourceNotFoundException("User with ID " + id + " not found")
            );

        TicketEntity updateInfo = 
            entityMapper.toEntity(ticket);

        requestedTicket.setCaller(updateInfo.getCaller());
        requestedTicket.setProblem(updateInfo.getProblem());
        requestedTicket.setDescription(updateInfo.getDescription());
        requestedTicket.setStatus(updateInfo.getStatus());
        requestedTicket.setAssignedTo(updateInfo.getAssignedTo());

        repository.save(requestedTicket);

        return entityMapper.toDomainObj(requestedTicket);
    }
}
