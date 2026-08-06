package com.project.helpdesk.infrastructure.gateways.ticket;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.domain.Page;

import com.project.helpdesk.application.gateways.TicketGateway;
import com.project.helpdesk.domain.entities.Ticket;
import com.project.helpdesk.domain.entities.User;
import com.project.helpdesk.domain.enums.TicketStatusEnum;
import com.project.helpdesk.domain.pagination.PaginatedResult;
import com.project.helpdesk.infrastructure.persistence.specification.TicketSpecification;
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
    public PaginatedResult<Ticket> listAllTickets(
        Long callerId,
        TicketStatusEnum status,
        Long assignedToId,
        Integer currentPage, 
        Integer pageSize
    ) {
        
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Specification<TicketEntity> spec = 
            TicketSpecification
                .withFilters(
                    callerId, 
                    status, 
                    assignedToId
                );

        Page<TicketEntity> entitiesPage = 
            repository.findAll(spec, pageable);


        Page<Ticket> page = 
            entitiesPage.map(entityMapper::toDomainObj);
            
        List<Ticket> tickets = page.getContent(); 

        return new PaginatedResult<>(
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

        requestedTicket.setCallerId(updateInfo.getCallerId());
        requestedTicket.setProblem(updateInfo.getProblem());
        requestedTicket.setDescription(updateInfo.getDescription());
        requestedTicket.setStatus(updateInfo.getStatus());
        requestedTicket.setAssignedToId(updateInfo.getAssignedToId());

        repository.save(requestedTicket);

        return entityMapper.toDomainObj(requestedTicket);
    }
}
