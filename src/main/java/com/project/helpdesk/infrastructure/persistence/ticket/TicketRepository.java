package com.project.helpdesk.infrastructure.persistence.ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.project.helpdesk.infrastructure.persistence.ticket.TicketEntity;

public interface TicketRepository 
    extends JpaRepository<TicketEntity, Long>, 
        JpaSpecificationExecutor<TicketEntity> {
    
}
