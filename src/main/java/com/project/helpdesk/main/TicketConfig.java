package com.project.helpdesk.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.project.helpdesk.application.useCases.*;
import com.project.helpdesk.application.gateways.TicketGateway;
import com.project.helpdesk.infrastructure.gateways.TicketEntityMapper;
import com.project.helpdesk.infrastructure.gateways.TicketRepositoryGateway;
import com.project.helpdesk.infrastructure.persistence.TicketRepository;
import com.project.helpdesk.infrastructure.controllers.DTOs.TicketDTOMapper;

@Configuration
public class TicketConfig {
    
    @Bean
    CreateTicketInteractor createTicketCase(TicketGateway ticketGateway) {
        return new CreateTicketInteractor(ticketGateway);
    }

    @Bean
    DeleteTicketInteractor deleteTicketCase(TicketGateway ticketGateway) {
        return new DeleteTicketInteractor(ticketGateway);
    }

    @Bean
    TicketGateway ticketGateway(TicketRepository ticketRepository, TicketEntityMapper ticketEntityMapper) {
      return new TicketRepositoryGateway(ticketRepository, ticketEntityMapper); 
    }

    @Bean
    TicketEntityMapper ticketEntityMapper() {
        return new TicketEntityMapper();
    }

    @Bean
    TicketDTOMapper ticketDTOMapper() {
        return new TicketDTOMapper();
    }
}
