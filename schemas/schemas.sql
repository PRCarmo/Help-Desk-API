CREATE SCHEMA IF NOT EXISTS user;
CREATE SCHEMA IF NOT EXISTS ticket;

-- IMPORTANTE: 
-- CASO O VOLUME JÁ EXISTA NO SEU DOCKER, RODE NO TERMINAL:
-- docker compose down
-- docker volume rm postgres_general_data
-- docker compose up