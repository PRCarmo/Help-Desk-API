package com.project.helpdesk.infrastructure.controllers.DTOs;

import java.time.LocalDateTime;

import com.project.helpdesk.domain.entities.User;
import com.project.helpdesk.domain.enums.TicketStatusEnum;

public record CreateTicketResponse(

    User caller,
    String problem,
    String description,
    TicketStatusEnum status,
    LocalDateTime createdAt,
    LocalDateTime solvedAt

) {}
