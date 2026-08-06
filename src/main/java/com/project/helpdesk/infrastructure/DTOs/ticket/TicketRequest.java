package com.project.helpdesk.infrastructure.DTOs.ticket;

import java.time.LocalDateTime;

import com.project.helpdesk.domain.enums.TicketStatusEnum;

public record TicketRequest(
    
    Long callerId, 
    String problem, 
    String description,
    TicketStatusEnum status,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    Long assignedToId

) {}
