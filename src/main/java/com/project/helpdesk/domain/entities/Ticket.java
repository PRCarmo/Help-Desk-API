package com.project.helpdesk.domain.entities;

import com.project.helpdesk.domain.enums.TicketStatusEnum;
import java.time.LocalDateTime;

public record Ticket(
     
    User caller, 
    String problem, 
    String description, 
    TicketStatusEnum status, 
    LocalDateTime createdAt, 
    LocalDateTime solvedAt,
    User assignedTo
    
) {}
