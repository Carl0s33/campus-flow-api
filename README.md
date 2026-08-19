# 🎓 Campus Flow API (Spring Boot + PostgreSQL)

Backend RESTful para o aplicativo **Campus Flow**, construído com **Java 17, Spring Boot 3, Spring Data JPA e PostgreSQL**.

---

## 🚀 Tecnologias

- **Java 17**
- **Spring Boot 3**
  - Spring Web (REST API)
  - Spring Data JPA (Hibernate)
  - Validation
  - Lombok
- **PostgreSQL**
- **Maven** (com Maven Wrapper mvnw)

---

## 📦 Como Rodar Localmente

### 1. Iniciar o Banco de Dados PostgreSQL via Docker

`ash
docker compose up -d
`

*(Ou utilize qualquer instância PostgreSQL local na porta 5432 com database campusflow_db, user postgres, senha postgres)*.

### 2. Rodar a API

No Windows:
`ash
./mvnw.cmd spring-boot:run
`

No Linux/macOS:
`ash
./mvnw spring-boot:run
`

A API estará disponível em: http://localhost:8080

---

## 📌 Endpoints da API

### Health Check
- GET / - Status da API

### Disciplinas (/api/disciplines)
- GET /api/disciplines - Listar todas
- GET /api/disciplines/{id} - Buscar por ID
- POST /api/disciplines - Criar disciplina
- PUT /api/disciplines/{id} - Atualizar disciplina
- PATCH /api/disciplines/{id}/absences - Atualizar faltas
- PATCH /api/disciplines/{id}/grades - Atualizar notas (N1/N2)
- DELETE /api/disciplines/{id} - Deletar disciplina

### Tarefas (/api/tasks)
- GET /api/tasks - Listar todas (suporta ?disciplineId=...)
- POST /api/tasks - Criar tarefa
- PATCH /api/tasks/{id}/toggle - Alternar status concluída/pendente
- PUT /api/tasks/{id} - Atualizar tarefa
- DELETE /api/tasks/{id} - Deletar tarefa

### Horários (/api/schedules)
- GET /api/schedules - Listar horários (suporta ?dayOfWeek=...)
- POST /api/schedules - Criar horário
- DELETE /api/schedules/{id} - Deletar horário

### Avaliações/Provas (/api/exams)
- GET /api/exams - Listar provas
- POST /api/exams - Criar prova
- DELETE /api/exams/{id} - Deletar prova

---

## 🌿 Estrutura GitFlow

- main: Branch de produção
- develop: Branch de integração e desenvolvimento
- eature/initial-spring-boot-setup: Branch com o setup inicial dos módulos
