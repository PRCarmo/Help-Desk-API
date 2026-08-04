# Help-Desk-API Versão O.1

Esse projeto é o backend de um helpdesk focado em suporte técnico de TI. É uma API REST, seguindo o padrão Clean Architecture.

## Índice

(ADAPTAR SISTEMA PARA COINCIDIR COM ESSE DOC (CALLER, ASSIGNEDTO, SCHEMAS))

- [Modelagem] (#Modelagem)
- [Arquitetura] (#Decisões-de-Arquitetura)
- [Stack] (#Stack-e-Motivações)
- [Executar] (#Rodando-e-Testando)
- [Relacionamentos] (#Modelo-Relacional)
- [Estrutura] (#Estrutura-de-Pacotes)
- [Expansões] (#Expansões-Futuras)
- [Correções] (#Correções-Intencionadas)
- [IA] (#Uso-de-IA-no-Projeto)

## Modelagem
- Entidades:
**Ticket**: A entidade central do sistema, contendo informações relevantes que cercam problemas problemas e sua eventual resolução, como `caller`, `problem`, `status`, `solvedAt`, `AssignedTo`, etc.
**User**: Entidade simples que possibilita interação com o sistema e complementa as funcionalidades e modelagem. Possui apenas o necessário para identificação e autenticação, como `name`, `password` e `role`.
- Recursos auxiliares:
**Pagination**: Pacote com que fornece paginação ao fluxo de Ticket, permitindo organização e consultas personalizadas.

## Decisões de Arquitetura
- **Clean architecture simplificada**: O uso dessa arquitetura permite a manter a separação entre domínio, aplicação e infraestrutura sem introduzir camadas desnecessárias para o projeto, permitindo flexibilidade enquanto evita complexidade. Isso potencializa adaptabilidade do sistema à novas stacks e ambientes, reduzindo esforço necessário para a mudança.
- **Banco único com schemas diferentes**: O necessário para os fluxos de cada entidade fica separado em schemas diferentes, sem a complexidade de ter que manejar diferentes bancos para cada uma.

## Stack e Motivações

- Java 21 -> Versão LTS utilizada pelo projeto dentro do escopo do Spring Boot
- Spring Boot -> Ferramenta moderna, densa e open-source. Ideal para o desenvolvimento de APIs REST em java.
- JWT -> Padrão de transmissão de informações em JSON, permitindo autenticação stateless e sendo ideal para SSO.
- PostgreSQL -> SGDB open-source e versátil que oferece diversas features e apoio da comunidade
- Swagger UI -> Simplifica a documentação da API, além de facilitar sua visualização e interação, permitindo personalizar estilo e funcionalidades.

## Rodando e Testando

### 1. Pré-requisitos

- Java 21
- Docker Desktop ou Docker Engine + Compose (Linux).

### 2. Subindo o conjunto banco + aplicação

Execute:

```bash
docker compose up -d
```

Depois de alguns segundos, execute:

```bash
docker compose ps
```

Deve haver dois containers - `helpdesk_db` e `helpdesk_app` - ambos com status `Up` e portas `5433:5433` e `5434:5434`, respectivamente.

A API estará disponível em: `http://localhost:5434/api/v1/`, com os principais endpoints sendo: `/users` e `/tickets`
   
### 3. Exemplo de fluxo utilizando cURL

OBS: Os exemplos assumem um banco recém-criado. Considera-se que Ana recebeu ID 1 e José recebeu ID 2.

**3.1 Criar um usuário**

```bash
curl --request POST \
  --url http://localhost:5434/api/v1/users \
  --header "Content-Type: application/json" \
  --data '{
    "name": "Ana",
    "password": "123456",
    "role": "EMPLOYEE"
}'
```

e

```bash
curl --request POST \
  --url http://localhost:5434/api/v1/users \
  --header "Content-Type: application/json" \
  --data '{
    "name": "José",
    "password": "abcdef",
    "role": "TECHNICIAN"
}'
```

**3.2 Criar um ticket**

```bash
curl --request POST \
  --url http://localhost:5434/api/v1/tickets \
  --header "Content-Type: application/json" \
  --data '{
    "callerId": 1,
    "problem": "Impressora não funciona",
    "description": "A impressora do setor financeiro não imprime documentos.",
    "status": "OPEN"
}'
```

**3.3 Listar tickets (com parâmetros)**

```bash
curl --request GET \
  --url "http://localhost:5434/api/v1/tickets?callerId=1&status=OPEN&currentPage=0&pageSize=20"
```

**3.4 Achar um ticket pelo ID**

```bash
curl --request GET \
  --url http://localhost:5434/api/v1/tickets/3
```

**3.5 Atualizar um ticket**

```bash
curl --request PUT \
  --url http://localhost:5434/api/v1/tickets/3 \
  --header "Content-Type: application/json" \
  --data '{
    "problem": "Impressora não funciona",
    "description": "Foi identificado atolamento de papel.",
    "status": "IN_PROGRESS",
    "assignedToId": 2
}'
```

e

```bash
curl --request PUT \
  --url http://localhost:5434/api/v1/tickets/3 \
  --header "Content-Type: application/json" \
  --data '{
    "problem": "Impressora não funciona",
    "description": "Atolamento de papel solucionado.",
    "status": "SOLVED",
    "assignedToId": 2
}'
```

**3.6 Deletar um ticket**

```bash
curl --request DELETE \
  --url http://localhost:5434/api/v1/tickets/3
```

### 4. Finalizando

Para finalizar:

```bash
docker compose down
```

Para apagar os volumes criados:

```bash
docker compose down -v
```

## Modelo Relacional

```
                              ┌─────────────┐
                              │    Ticket   │
┌─────────────┐               │             │
│   User      │               │ id          │
│             │ 1           N │ caller      │
│ id          ├───────────────┤ problem     │
│ name        │  cria/altera  │ description │
│ password    │               │ status      │
│ role        │               │ solvedAt    │
└─────────────┘               │ createdAt   │                      
                              │ assignedTo  │
                              └─────────────┘
```

Ticket -> Caller -> Referência à User
Ticket -> AssignedTo -> Referência à User

## Estrutura de Pacotes

helpdeskapi/
├── docker-compose.yml                    # Serviços: postgres e aplicação
├── dockerfile                            # Build multi-stage: Maven → JDK Alpine
├── pom.xml                               # Maven, Spring Boot 4.0.7, Java 21
├── README.md                             # Este arquivo
│
├── src/main/java/com/project/helpdesk/
│   ├── HelpdeskApplication.java          # Arquivo de execução do Spring Boot
│   ├── application/                      # UseCases, gateways
│   ├── domain/                           # entities, enums, pagination
│   ├── infrastructure/                   
│       └── config/                       # Injeção de dependências do Spring
│       └── DTOs/                         # Concentra DTOs e mappers de ambas entidades
│       └── gateways/                     # Define o contrato dos UseCases
│       └── persistence/                  # Entidades de persistência, repositories e specification
│   ├── presentation/                     # Controllers
├── src/main/resources/
│   ├── application.properties            # Configuração docker
│
└── src/test/                             # Testes unitários e de integração

## Expansões Futuras

**Documentação Interativa**: Swagger UI será integrado ao sistema para permitir sincronia automática entre o código e a documentação, facilitando o entendimento da API.
**Autenticação**: Será criado um fluxo que cuide do login e autenticação dos usuários, além de verificação de permissões para utilizar cada endpoint do sistema.
**Logs**: Uma funcionalidade que permita registrar (automaticamente) e acessar ações realizadas durante o uso do sistema. Hibernate Envers será a principal ferramenta para isso.
**Testes**: O projeto contará com testes unitários e de integração para garantir robustez e qualidade do código. JUnit, Mockito e Test Containers serão usados para isso.
**Migration**: Para possibilitar o versionamento do banco de dados e oferecer melhor integração com CI/CD e testes, o sistema utilizará o Flyway futuramente.

## Próximas Melhorias

- A maioria dos métodos definidos nos RepositoryGateway's podem jogar ResourceNotFoundException, que não foi definida ainda. Essa exceção, assim como outras, serão definidas na próxima versão do projeto.
- Ainda é necessário expandir as regras de negócio da aplicação para permitir uso mais fluido e intuitivo do software e seus endpoints, algo que será traduzido em operações PATCH na próxima versão do sistema.
- Adicionais: refatoração da estrutura, limpeza de código, etc.

## Uso de IA no Projeto

- A IA foi utilizada neste projeto como uma ferramenta de aprendizado e correção de raciocínio e código. 
- No tocante às decisões que não envolveram imediatamente o código (arquitetura, modelagem, stack), ela foi usada de forma exploratória para validar raciocínios, tirar dúvidas, pesar trade-offs e demonstrar possibilidades de implementação. 
- Já no que se refere a codificação, ela foi utilizada como meio de orientação (em avaliação de decisões de implementação e separação de responsabilidades) e de correção (debugging, refatoração e limpeza).
- Todo o código que não foi escrito pelo autor foi cuidadosamente questionado, compreendido e revisado.
- Modelos utilizados: GPT-5.1
