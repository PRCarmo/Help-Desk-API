# Help-Desk-API Versão O.1

(COMPLETAR E REVISAR)

# Resumo
Esse projeto é o backend de um helpdesk focado em suporte técnico de TI. É uma API REST, seguindo o padrão Clean Architecture.

# Modelagem
- Entidades:
**Ticket**: A entidade central do sistema, contendo informações relevantes que cercam problemas problemas e sua eventual resolução, como `caller`, `problem`, `status`, `solvedAt`, `AssignedTo`, etc.
**User**: Entidade simples que possibilita interação com o sistema e complementa as funcionalidades e modelagem. Possui apenas o necessário para identificação e autenticação, como `name`, `password` e `role`.
- Recursos auxiliares:
**Pagination**:

# Decisões de Arquitetura
- **Clean architecture simplificada**: O uso dessa arquitetura permite a independência de detalhes como frameworks e bancos de dados enquanto evita a complexidade que acompanha o modelo tradicional, unindo assim o melhor dos dois mundos. Isso potencializa adaptabilidade do sistema à novas stacks e ambientes, reduzindo esforço necessário para tal.
- **Banco único com schemas diferentes**: O necessário para os fluxos de cada entidade fica separado em schemas diferentes, sem a complexidade de ter que manejar diferentes bancos para cada uma.

# Stack e Motivações

- Java 21 -> Runtime moderno dentro do escopo do springboot
- Springboot -> Ferramenta moderna, densa e open-source. Ideal para o desenvolvimento de APIs REST em java.
- JWT -> Padrão de transmissão de informações em JSON, permitindo autenticação stateless e sendo ideal para SSO.
- PostgreSQL -> Sistema de gerenciamento ORM open-source e versátil que oferece diversas features e apoio da comunidade
- Swagger UI -> Simplifica a documentação da API, além de facilitar sua visualização e interação, permitindo personalizar estilo e funcionalidades.

# Como Rodar

# Modelo Relacional

# Estrutura de pacotes

helpdeskapi/
├── docker-compose.yml                    # Postgres e aplicação
├── dockerfile                            # Build multi-stage: Maven → JDK Alpine
├── pom.xml                               # Maven, Spring Boot 4.0.7, Java 21
├── README.md                             # Este arquivo
│
├── src/main/java/com/project/helpdesk/
│   ├── application/                      # UseCases, gateways
│   ├── domain/                           # entities, enums, pagination
│   ├── infrastructure/                   
│       └── config/
│       └── DTOs/                         # **Completar essas hashtags aqui**
│       └── gateways/
│       └── persistence/
│   ├── presentation/                     # Controllers
├── src/main/resources/
│   ├── application.properties            
│
└── src/test/                             # Testes unitários e de integração

# Expansões e Stack Futuras

# Correções Intencionadas

# Uso de IA na Composição do Projeto

- A IA foi utilizada neste projeto como uma ferramenta de aprendizado e correção de racicínio e código. 
- No tocante às decisões que não envolveram imediatamente o código (arquitetura, modelagem, stack), ela foi usada de forma exploratória para validar raciocínios, tirar dúvidas, pesar trade-offs e demonstrar possibilidades de implementação. 
- Já no que se refere a codificação, ela foi utilizada como meio de orientação (em avaliação de decisões de implementação e separação de responsabilidades) e de correção (debugging, refatoração e limpeza).
- Todo o código que não foi escrito pelo autor foi cuidadosamente questionado, compreendido e revisado.
- Modelos utilizados: GPT-5.1
