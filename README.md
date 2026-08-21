Campus Flow API ⚙️

## Visão Geral do Projeto

O **Campus Flow API** é o núcleo de inteligência de dados e persistência do ecossistema Campus Flow. Trata-se de uma API RESTful desenvolvida em Java com Spring Boot, projetada para orquestrar a complexa gestão de horários, disciplinas, avaliações e tarefas de estudantes universitários.

> **Aviso de Ecossistema:** Este repositório foca exclusivamente no backend e infraestrutura. Para o aplicativo de interface de usuário, consulte o repositório irmão: [Campus Flow Mobile](https://github.com/carl0s33/campus-flow-mobile-_2) 

O design da API garante alta coesão e baixo acoplamento, fornecendo endpoints estruturados, seguros e escaláveis para o consumo do cliente mobile.

## Arquitetura de Software

A aplicação segue o padrão de Arquitetura em Camadas (Layered Architecture):

*   **Controllers (`controller/`):** Camada de apresentação REST (Ex: `DisciplineController`, `ExamController`, `HealthController`).
*   **Services (`service/`):** Validação e regras de negócio isoladas da persistência.
*   **Repositories (`domain/repository/`):** Interfaces DAO via Spring Data JPA.
*   **Models (`domain/model/`):** Entidades ORM mapeadas para o PostgreSQL.
*   **DTOs (`dto/`):** Data Transfer Objects para prevenir *over-posting* e blindar o domínio.

### Tratamento de Exceções
Implementação de um `GlobalExceptionHandler` para padronizar respostas de erro HTTP (ex: `EntityNotFoundException`), garantindo robustez na integração com o frontend.

## Pilha Tecnológica (Tech Stack)

*   **Linguagem:** Java.
*   **Framework:** Spring Boot.
*   **ORM:** Spring Data JPA / Hibernate.
*   **Banco de Dados:** PostgreSQL.
*   **Migrações:** Ferramenta de versionamento SQL automatizada (ex: Flyway).
*   **Infraestrutura:** Docker e Docker Compose.

## Banco de Dados e Migrações

A modelagem é garantida via scripts locais em `src/main/resources/db/migration/`:

*   `V1__create_tables.sql`: DDL de tabelas e constraints.
*   `V2__insert_initial_mock_data.sql`: Dados essenciais para ambiente de desenvolvimento.
*   `V3__insert_all_tads_disciplines.sql`: Script estratégico que preenche a base com toda a grade curricular de TADS (Análise e Desenvolvimento de Sistemas) do IFRN. Isso acelera o setup para o público-alvo principal, testando o banco de dados em um cenário de produção real.

## Configuração e Execução Local

### Pré-requisitos
*   JDK instalado.
*   Docker e Docker Compose operantes no host.

### Passos
1.  **Clone o Repositório.**
2.  **Variáveis de Ambiente:** Crie o arquivo `.env` baseado em `.env.example`.
3.  **Suba a Infraestrutura (PostgreSQL):**
    ```bash
    docker-compose up -d
    ```
4.  **Inicie a Aplicação:** Utilize o Maven Wrapper. Ideal para ambientes focados em performance no Linux, dispensa instalação global do Maven:
    *Linux / macOS:*
    ```bash
    ./mvnw spring-boot:run
    ```
    *Windows:*
    ```cmd
    mvnw.cmd spring-boot:run
    ```
5.  **Health Check:** Verifique a porta local para assegurar a prontidão via `HealthController`.

## Integração (CORS)
A comunicação com a rede local ou clientes externos está definida em `CorsConfig.java`, permitindo fácil comunicação com o React Native durante o desenvolvimento.

---
*Licença contida no arquivo LICENSE do repositório.*
