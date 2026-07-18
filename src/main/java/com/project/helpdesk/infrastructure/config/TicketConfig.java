package com.project.helpdesk.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.project.helpdesk.application.useCases.ticket.*;
import com.project.helpdesk.application.gateways.TicketGateway;
import com.project.helpdesk.infrastructure.DTOs.ticket.TicketDTOMapper;
import com.project.helpdesk.infrastructure.gateways.ticket.TicketEntityMapper;
import com.project.helpdesk.infrastructure.gateways.ticket.TicketRepositoryGateway;
import com.project.helpdesk.infrastructure.persistence.ticket.TicketRepository;

@Configuration
public class TicketConfig {
    // O código desses arquivos de configuração 
    // substitui o uso de @Autowired para injeção de dependências (beans)
    // em outras partes do código, reforçando o desacoplamento
    @Bean
    CreateTicketInteractor createTicketCase(TicketGateway ticketGateway) {
        return new CreateTicketInteractor(ticketGateway);
    }

    @Bean
    DeleteTicketInteractor deleteTicketCase(TicketGateway ticketGateway) {
        return new DeleteTicketInteractor(ticketGateway);
    }

    @Bean
    GetTicketByIdInteractor getTicketByIdCase(TicketGateway ticketGateway) {
        return new GetTicketByIdInteractor(ticketGateway);
    }

    @Bean
    ListAllTicketsInteractor listAllTicketsInteractor(TicketGateway ticketGateway) {
        return new ListAllTicketsInteractor(ticketGateway);
    }

    @Bean
    UpdateTicketInteractor updateTicketInteractor(TicketGateway ticketGateway) {
        return new UpdateTicketInteractor(ticketGateway);
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
