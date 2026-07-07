package com.project.helpdesk.infrastructure.persistence;

import java.time.LocalDateTime;
import com.project.helpdesk.domain.entities.User;
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

    private User caller;

    private String problem;
    
    private String description;
    
    private TicketStatusEnum status;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime solvedAt;

    public TicketEntity(User caller, String problem, String description, TicketStatusEnum status, LocalDateTime createdAt, LocalDateTime solvedAt) {
        this.caller = caller;
        this.problem = problem;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.solvedAt = solvedAt;
    }
    
}
