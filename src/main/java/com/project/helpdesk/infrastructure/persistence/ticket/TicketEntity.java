package com.project.helpdesk.infrastructure.persistence.ticket;

import java.time.LocalDateTime;

import com.project.helpdesk.domain.enums.TicketStatusEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Tickets", schema = "ticket")
@Data
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Caller_id", updatable = false) // inserir insertable false posteriormente
    private Long callerId;

    @Column(name = "Problem")
    private String problem;
    
    @Column(name = "Description")
    private String description;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    private TicketStatusEnum status;
    
    @Column(name = "Created_at") // inserir annotations localdatetime posteriormente
    private LocalDateTime createdAt;
    
    @Column(name = "Updated_AT")
    private LocalDateTime updatedAt;

    @Column(name = "Assigned_to_id") // mesmo de callerId
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
