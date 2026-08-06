package com.project.helpdesk.infrastructure.persistence.ticket;

import java.time.LocalDateTime;

import com.project.helpdesk.domain.enums.TicketStatusEnum;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long callerId;

    private String problem;
    
    private String description;
    
    private TicketStatusEnum status;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;

    private Long assignedToId;

    public TicketEntity(
        Long callerId, 
        String problem, 
        String description, 
        TicketStatusEnum status, 
        LocalDateTime createdAt, 
        LocalDateTime updatedAt,
        long assignedToId
        ) {
            this.callerId = callerId;
            this.problem = problem;
            this.description = description;
            this.status = status;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
            this.assignedToId = assignedToId;
    }
    
}
